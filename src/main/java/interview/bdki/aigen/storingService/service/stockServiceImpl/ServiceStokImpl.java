package interview.bdki.aigen.storingService.service.stockServiceImpl;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;

import interview.bdki.aigen.storingService.entity.EntityStock;
import interview.bdki.aigen.storingService.helper.CekPermissionFileUpload;
import interview.bdki.aigen.storingService.helper.GenerateTimeFormatIdn;
import interview.bdki.aigen.storingService.helper.SaveImageItem;
import interview.bdki.aigen.storingService.payload.request.addItem.RequestAddItemStock;
import interview.bdki.aigen.storingService.payload.request.deleteStock.RequestDeleteStock;
import interview.bdki.aigen.storingService.payload.request.getItem.RequestGetItemStock;
import interview.bdki.aigen.storingService.payload.request.stock.RequestStock;
import interview.bdki.aigen.storingService.payload.request.updateStock.RequestUpdateStock;
import interview.bdki.aigen.storingService.payload.response.Response;
import interview.bdki.aigen.storingService.repository.RepositoryStock;
import interview.bdki.aigen.storingService.service.ServiceStock;
import jakarta.transaction.Transactional;

import java.util.Map;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class ServiceStokImpl implements ServiceStock {

    @Autowired
    private RepositoryStock repositoryStock;

    @Autowired
    GenerateTimeFormatIdn generateTimeFormatIdn;

    @Autowired
    CekPermissionFileUpload cekPermissionFileUpload;

    @Autowired
    SaveImageItem saveImageItem;

    @Override
    public Response<Map<String, Object>> processAddItem(RequestAddItemStock requestAddItemStock) throws Exception {
        if (requestAddItemStock == null) {
            return new Response<>("ERROR", "99", "Request cannot be null", null);
        }

        EntityStock entityStock = repositoryStock.findBySerialNumber(requestAddItemStock.getSerialNumber());
        if (entityStock != null) {
            return new Response<>("ERROR", "01", "Item with this serial number already exists", null);
        }

        String urlImageItem = requestAddItemStock.getImageItem();
        if (urlImageItem != null) {
            String fileExtension = cekPermissionFileUpload.getFileExtension(urlImageItem);
            if (!fileExtension.equalsIgnoreCase("jpg") && !fileExtension.equalsIgnoreCase("png")) {
                return new Response<>("ERROR", "01", "Only .jpg and .png files are allowed", null);
            }
        }
        // function save file
        String urlPathImageString = saveImageItem.saveFileIntoCloud(urlImageItem);

        entityStock = new EntityStock();
        entityStock.setNameItem(requestAddItemStock.getName());
        entityStock.setQuantity(requestAddItemStock.getQuantity());
        entityStock.setSerialNumber(requestAddItemStock.getSerialNumber());

        // Convert additional info to JSON string
        ObjectMapper obj = new ObjectMapper();
        String additionalInfo = obj.writeValueAsString(requestAddItemStock.getAdditionalInfo());
        entityStock.setAdditionalInfo(additionalInfo);
        entityStock.setImageUrl(urlPathImageString);
        entityStock.setCreatedBy("users");
        entityStock.setCreatedAt(generateTimeFormatIdn.generateTimeWithFormatIdn(new Date()));

        try {
            repositoryStock.save(entityStock);
            RequestStock dataStored = new RequestStock();
            dataStored.setId(entityStock.getId());
            dataStored.setName(entityStock.getNameItem());
            dataStored.setQuantity(entityStock.getQuantity());
            dataStored.setSerialNumber(entityStock.getSerialNumber());
            dataStored.setImageUrl(entityStock.getImageUrl());
            dataStored.setCreatedBy(entityStock.getCreatedBy());
            dataStored.setCreatedAt(entityStock.getCreatedAt());
            dataStored.setUpdatedAt(entityStock.getUpdatedAt());
            dataStored.setUpdatedBy(entityStock.getUpdatedBy());

            @SuppressWarnings("unchecked")
            Map<String, Object> additionalInfoMap = obj.readValue(entityStock.getAdditionalInfo(), Map.class);
            dataStored.setAdditionalInfo(additionalInfoMap);

            Map<String, Object> dataSuccess = new LinkedHashMap<>();
            dataSuccess.put("detailed", dataStored);
            return new Response<>("SUCCESS", "00", "Input item successfully", dataSuccess);

        } catch (Exception e) {
            Map<String, Object> data = new LinkedHashMap<>();
            data.put("reason error", e.getMessage());
            return new Response<>("ERROR", "02", "Failed to add item", data);
        }
    }

    @Override
    public Response<Map<String, Object>> processGetAllItems() throws Exception {
        ObjectMapper obj = new ObjectMapper();
        List<EntityStock> entityStocks = repositoryStock.findAll();

        if (entityStocks.isEmpty()) {
            Map<String, Object> detailed = new LinkedHashMap<>();
            detailed.put("items", entityStocks);
            detailed.put("totalItem", 0);
            Map<String, Object> dataRetriveMMap = new HashMap<>();
            dataRetriveMMap.put("detailed", detailed);

            return new Response<>("ERROR", "01", "No items found in the database", dataRetriveMMap);
        }

        List<RequestStock> itemsWithAdditionalInfo = new ArrayList<>();
        for (EntityStock stock : entityStocks) {
            RequestStock itemInfo = new RequestStock();
            itemInfo.setId(stock.getId());
            itemInfo.setName(stock.getNameItem());
            itemInfo.setQuantity(stock.getQuantity());
            itemInfo.setSerialNumber(stock.getSerialNumber());
            itemInfo.setImageUrl(stock.getImageUrl());
            itemInfo.setCreatedBy(stock.getCreatedBy());
            itemInfo.setCreatedAt(stock.getCreatedAt());
            itemInfo.setUpdatedAt(stock.getUpdatedAt());
            itemInfo.setUpdatedBy(stock.getUpdatedBy());

            @SuppressWarnings("unchecked")
            Map<String, Object> additionalInfoMap = obj.readValue(stock.getAdditionalInfo(), Map.class);
            itemInfo.setAdditionalInfo(additionalInfoMap);
            itemsWithAdditionalInfo.add(itemInfo);
        }

        // Prepare the final response
        Map<String, Object> detailed = new HashMap<>();
        detailed.put("items", itemsWithAdditionalInfo);
        detailed.put("totalItem", entityStocks.size());
        Map<String, Object> dataRetriveMMap = new HashMap<>();
        dataRetriveMMap.put("detailed", detailed);

        return new Response<>("SUCCESS", "00", "Items retrieved successfully", dataRetriveMMap);
    }

    @Override
    public Response<Map<String, Object>> processGetItemStock(RequestGetItemStock requestGetItem) throws Exception {
        ObjectMapper obj = new ObjectMapper();
        if (requestGetItem == null) {
            return new Response<>("ERROR", "99", "Request cannot be null", null);
        }

        String name = requestGetItem.getName();
        String serialNumber = requestGetItem.getSerialNumber();

        List<EntityStock> entityStocks = repositoryStock.findByNameItemAndSerialNumber(name, serialNumber);

        if (!entityStocks.isEmpty()) {
            List<RequestStock> detailedList = new ArrayList<>();
            for (EntityStock stock : entityStocks) {
                RequestStock detailed = new RequestStock();
                detailed.setId(stock.getId());
                detailed.setName(stock.getNameItem());
                detailed.setQuantity(stock.getQuantity());
                detailed.setSerialNumber(stock.getSerialNumber());
                detailed.setImageUrl(stock.getImageUrl());
                detailed.setCreatedBy(stock.getCreatedBy());
                detailed.setCreatedAt(stock.getCreatedAt());
                detailed.setUpdatedAt(stock.getUpdatedAt());
                detailed.setUpdatedBy(stock.getUpdatedBy());

                @SuppressWarnings("unchecked")
                Map<String, Object> additionalInfoMap = obj.readValue(stock.getAdditionalInfo(), Map.class);
                detailed.setAdditionalInfo(additionalInfoMap);

                detailedList.add(detailed);
            }
            Map<String, Object> dataRetriveMMap = new LinkedHashMap<>();
            dataRetriveMMap.put("detailed", detailedList);

            return new Response<>("SUCCESS", "00", "Items retrieved successfully", dataRetriveMMap);
        }
        return new Response<>("ERROR", "01", "Item not found", null);
    }

    @Override
    public Response<Map<String, Object>> processUpdateStock(RequestUpdateStock requestUpdateStock) throws Exception {
        Date date = new Date();
        ObjectMapper obj = new ObjectMapper();
        EntityStock existingStock = repositoryStock.findBySerialNumber(requestUpdateStock.getSerialNumber());

        if (existingStock == null) {
            Map<String, Object> data = new HashMap<>();
            return new Response<>("ERROR", "02", "Stock item not found", data);
        }

        if (requestUpdateStock.getName() != null) {
            existingStock.setNameItem(requestUpdateStock.getName());
        }

        if (requestUpdateStock.getQuantity() != null) {
            existingStock.setQuantity(requestUpdateStock.getQuantity());
        }

        if (requestUpdateStock.getSerialNumber() != null &&
                !requestUpdateStock.getSerialNumber().equals(existingStock.getSerialNumber())) {
            return new Response<>("ERROR", "03", "Serial number cannot be changed", null);
        }

        existingStock.setUpdatedAt(generateTimeFormatIdn.generateTimeWithFormatIdn(date));
        existingStock.setUpdatedBy("users");
        repositoryStock.save(existingStock);
        
        RequestStock rebuiltResponse = new RequestStock();
        rebuiltResponse.setId(existingStock.getId());
        rebuiltResponse.setName(existingStock.getNameItem());
        rebuiltResponse.setQuantity(existingStock.getQuantity());
        rebuiltResponse.setSerialNumber(existingStock.getSerialNumber());
        rebuiltResponse.setImageUrl(existingStock.getImageUrl());
        rebuiltResponse.setCreatedBy(existingStock.getCreatedBy());
        rebuiltResponse.setCreatedAt(existingStock.getCreatedAt());
        rebuiltResponse.setUpdatedAt(existingStock.getUpdatedAt());
        rebuiltResponse.setUpdatedBy(existingStock.getUpdatedBy());

        @SuppressWarnings("unchecked")
        Map<String, Object> additionalInfoMap = obj.readValue(existingStock.getAdditionalInfo(), Map.class);
        rebuiltResponse.setAdditionalInfo(additionalInfoMap);

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("detailed", rebuiltResponse);
        return new Response<>("SUCCESS", "00", "Stock updated successfully", data);
    }

    @Override
    @Transactional
    public Response<Map<String, Object>> processDeletedStock(RequestDeleteStock requestDeleteStock) throws Exception {
        try {
            EntityStock stock = repositoryStock.findBySerialNumber(requestDeleteStock.getSerialNumber());
            if (stock != null) {
                repositoryStock.deleteBySerialNumber(requestDeleteStock.getSerialNumber());
                Map<String, Object> data = new HashMap<>();
                return new Response<>("SUCCESS", "00", "Stock deleted successfully", data);
            } else {
                Map<String, Object> data = new HashMap<>();
                return new Response<>("ERROR", "01", "Stock not found", data);
            }
        } catch (DataIntegrityViolationException e) {
            Map<String, Object> data = new HashMap<>();
            return new Response<>("ERROR", "02", "Failed to delete stock due to data integrity violation", data);
        } catch (Exception e) {
            Map<String, Object> data = new HashMap<>();
            return new Response<>("ERROR", "03", "Failed to delete stock", data);
        }
    }

}
