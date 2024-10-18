package interview.bdki.aigen.storingService.service;

import interview.bdki.aigen.storingService.payload.request.addItem.RequestAddItemStock;
import interview.bdki.aigen.storingService.payload.request.deleteStock.RequestDeleteStock;
import interview.bdki.aigen.storingService.payload.request.getItem.RequestGetItemStock;
import interview.bdki.aigen.storingService.payload.request.updateStock.RequestUpdateStock;
import interview.bdki.aigen.storingService.payload.response.Response;

import java.util.Map;




public interface ServiceStock {

    Response<Map<String,Object>> processAddItem(RequestAddItemStock requestAddItemStock) throws Exception;

    Response<Map<String, Object>> processGetAllItems() throws Exception;

    Response<Map<String, Object>> processGetItemStock(RequestGetItemStock requestGetItem) throws Exception;

    Response<Map<String, Object>> processUpdateStock(RequestUpdateStock requestUpdateStock) throws Exception;

    Response<Map<String, Object>> processDeletedStock(RequestDeleteStock requestDeleteStock) throws Exception;
}
