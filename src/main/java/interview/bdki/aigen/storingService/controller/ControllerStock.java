package interview.bdki.aigen.storingService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


import interview.bdki.aigen.storingService.helper.LogInfoFormat;
import interview.bdki.aigen.storingService.payload.request.addItem.RequestAddItemStock;
import interview.bdki.aigen.storingService.payload.request.deleteStock.RequestDeleteStock;
import interview.bdki.aigen.storingService.payload.request.getItem.RequestGetItemStock;
import interview.bdki.aigen.storingService.payload.request.updateStock.RequestUpdateStock;
import interview.bdki.aigen.storingService.payload.response.Response;
import interview.bdki.aigen.storingService.service.ServiceStock;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/stock")
public class ControllerStock {
    @Autowired
    ServiceStock serviceStock;

    @Autowired
    LogInfoFormat logInfoFormat;

    private Logger LOGGER = LoggerFactory.getLogger(ControllerStock.class);

    @PostMapping("/addItem")
    public ResponseEntity<Response<Map<String, Object>>> addItem(@RequestBody RequestAddItemStock request)throws Exception {
        Response<Map<String, Object>> response = serviceStock.processAddItem(request);
        
        if(response.getResponseCode().equals("00")){
            String logInfo = logInfoFormat.setLogInfo("Accees stock/addItem",String.valueOf(request), String.valueOf(response),"localhost:8080/stock/addItem","","", String.valueOf(HttpStatus.OK));
            LOGGER.info(logInfo);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            String logInfo = logInfoFormat.setLogInfo("Accees stock/addItem",String.valueOf(request), String.valueOf(response),"localhost:8080/stock/addItem","","", String.valueOf(HttpStatus.BAD_REQUEST));
            LOGGER.info(logInfo);
            response = new Response<>("ERROR", response.getResponseCode(), response.getResponseMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/getAll")
    public ResponseEntity<Response<Map<String, Object>>> getAllItems() throws Exception{
        Response<Map<String, Object>> response = serviceStock.processGetAllItems();
        if(response.getResponseCode().equals("00")){
            String logInfo = logInfoFormat.setLogInfo("Accees stock/getAll","", String.valueOf(response),"localhost:8080/stock/getAll","","", String.valueOf(HttpStatus.OK));
            LOGGER.info(logInfo);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            String logInfo = logInfoFormat.setLogInfo("Accees stock/getAll","", String.valueOf(response),"localhost:8080/stock/getAll","","", String.valueOf(HttpStatus.BAD_REQUEST));
            LOGGER.info(logInfo);
            response = new Response<>("ERROR", response.getResponseCode(), response.getResponseMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/getItem")
    public ResponseEntity<Response<Map<String, Object>>> getItem(@RequestBody RequestGetItemStock request) throws Exception {
        Response<Map<String, Object>> response =  serviceStock.processGetItemStock(request);
        if(response.getResponseCode().equals("00")){
            String logInfo = logInfoFormat.setLogInfo("Accees stock/getItem",String.valueOf(request), String.valueOf(response),"localhost:8080/stock/getItem","","", String.valueOf(HttpStatus.OK));
            LOGGER.info(logInfo);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            String logInfo = logInfoFormat.setLogInfo("Accees stock/getItem",String.valueOf(request), String.valueOf(response),"localhost:8080/stock/getItem","","", String.valueOf(HttpStatus.BAD_REQUEST));
            LOGGER.info(logInfo);
            response = new Response<>("ERROR", response.getResponseCode(), response.getResponseMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/updateStockItem")
    public ResponseEntity<Response<Map<String, Object>>> updateStockItemm(@RequestBody RequestUpdateStock request) throws Exception {
        Response<Map<String, Object>> response = serviceStock.processUpdateStock(request);
        if(response.getResponseCode().equals("00")){
            String logInfo = logInfoFormat.setLogInfo("Accees stock/updateStockItem",String.valueOf(request), String.valueOf(response),"localhost:8080/stock/updateStockItem","","", String.valueOf(HttpStatus.OK));
            LOGGER.info(logInfo);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            String logInfo = logInfoFormat.setLogInfo("Accees stock/updateStockItem",String.valueOf(request), String.valueOf(response),"localhost:8080/stock/updateStockItem","","", String.valueOf(HttpStatus.BAD_REQUEST));
            LOGGER.info(logInfo);
            response = new Response<>("ERROR", response.getResponseCode(), response.getResponseMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/deletedStock")
    public ResponseEntity<Response<Map<String, Object>>> deletedStock(@RequestBody RequestDeleteStock request) throws Exception {
        Response<Map<String, Object>> response = serviceStock.processDeletedStock(request);
        if(response.getResponseCode().equals("00")){
            String logInfo = logInfoFormat.setLogInfo("Accees stock/deletedStock",String.valueOf(request), String.valueOf(response),"localhost:8080/stock/deletedStock","","", String.valueOf(HttpStatus.OK));
            LOGGER.info(logInfo);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            String logInfo = logInfoFormat.setLogInfo("Accees stock/deletedStock",String.valueOf(request), String.valueOf(response),"localhost:8080/stock/deletedStock","","", String.valueOf(HttpStatus.BAD_REQUEST));
            LOGGER.info(logInfo);
            response = new Response<>("ERROR", response.getResponseCode(), response.getResponseMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    
}
