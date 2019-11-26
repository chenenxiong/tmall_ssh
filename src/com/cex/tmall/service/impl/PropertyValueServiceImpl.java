package com.cex.tmall.service.impl;

import com.cex.tmall.pojo.Product;
import com.cex.tmall.pojo.Property;
import com.cex.tmall.pojo.PropertyValue;
import com.cex.tmall.service.BaseService;
import com.cex.tmall.service.PropertyService;
import com.cex.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyValueServiceImpl extends BaseServiceImpl implements PropertyValueService {
    @Autowired
    PropertyService propertyService;

    @Override
    public void init(Product product) {
        List<Property> propertyList =propertyService.listByParent(product.getCategory());
        for(Property property:propertyList){
            PropertyValue propertyValue = get(property,product);
            if(null==propertyValue){
                propertyValue = new PropertyValue();
                propertyValue.setProduct(product);
                propertyValue.setProperty(property);
                save(propertyValue);
            }
        }
    }

    private PropertyValue get(Property property, Product product) {
        List<PropertyValue> propertyValueList = list("property",property,"product",product);
        if(propertyValueList.isEmpty()) return null;
        return propertyValueList.get(0);
    }
}
