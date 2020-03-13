package com.ohenzy.warehouse.storage.util;

import com.ohenzy.warehouse.models.InvoiceProduct;
import com.ohenzy.warehouse.models.Product;
import com.ohenzy.warehouse.storage.WarehouseStorage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.LinkedList;
import java.util.List;


public class JsonParser {
    private final WarehouseStorage warehouses = new WarehouseStorage();
    private final JSONArray nameProductList;
    private final JSONArray warehouseList;
    private final JSONArray quantityList;
    private final JSONArray unitList;
    private final JSONArray priceList;
    private final String typeInvoice;


    public JsonParser(String jsonString) {

        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) new JSONParser().parse(jsonString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        nameProductList = (JSONArray) jsonObject.get("nameProduct");
        warehouseList = (JSONArray) jsonObject.get("warehouse");
        quantityList = (JSONArray) jsonObject.get("quantity");
        unitList = (JSONArray) jsonObject.get("unit");
        priceList = (JSONArray) jsonObject.get("price");
        typeInvoice = (String) jsonObject.get("typeInvoice");
    }

    public List<Product> getProducts() {
        List<Product> productList = new LinkedList<>();

        for(int i = 0;i<nameProductList.size();i++) {
            productList.add(new Product(
                    nameProductList.get(i).toString(),
                    Integer.parseInt(quantityList.get(i).toString()),
                    unitList.get(i).toString()
            ));
        }
        if(typeInvoice.equals("расход"))
            for(Product product : productList)
                product.setQuantity(product.getQuantity() * -1);

        return productList;
    }

    public List<InvoiceProduct> getInvoiceProducts() {
        List<InvoiceProduct> partProductsList = new LinkedList<>();
        for(int i = 0;i<nameProductList.size();i++) {
            partProductsList.add(new InvoiceProduct(
                    nameProductList.get(i).toString(),
                    Integer.parseInt(quantityList.get(i).toString()),
                    unitList.get(i).toString(),
                    Integer.parseInt(priceList.get(i).toString()),
                    warehouses.findById(Integer.parseInt(warehouseList.get(i).toString()))
            ));
        }
        return partProductsList;
    }
}
