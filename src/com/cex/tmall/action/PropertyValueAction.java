package com.cex.tmall.action;

import com.cex.tmall.pojo.Product;
import com.cex.tmall.pojo.PropertyValue;
import org.apache.struts2.convention.annotation.Action;

public class PropertyValueAction extends Action4Result{
    @Action("admin_propertyValue_edit")
    public String edit(){
        int id = product.getId();
        product= (Product) productService.get(id);
        propertyValueService.init(product);
        propertyValues = propertyValueService.listByParent(product);
        return "editPropertyValue";
    }

    @Action("admin_propertyValue_update")
    public String update(){
       String value =  propertyValue.getValue();
       int id = propertyValue.getId();
       propertyValue = (PropertyValue) propertyValueService.get(id);
       propertyValue.setValue(value);
       propertyValueService.update(propertyValue);
        return "success.jsp";
    }
}
