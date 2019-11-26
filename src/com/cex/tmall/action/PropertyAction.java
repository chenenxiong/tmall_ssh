package com.cex.tmall.action;

import com.cex.tmall.pojo.Category;
import com.cex.tmall.pojo.Property;
import com.cex.tmall.util.Page;
import org.apache.struts2.convention.annotation.Action;

public class PropertyAction extends Action4Result {
    @Action("admin_property_list")
    public String list(){
        if(page==null) page = new Page();
        int total=propertyService.total(category);
        page.setTotal(total);
        page.setParam("&category.id="+category.getId());
        propertys=propertyService.list(page,category);
        int id = category.getId();
        category= (Category) categoryService.get(id);
        return  "listProperty";
    }

    @Action("admin_property_edit")
    public String edit(){
        int id = property.getId();
        property= (Property) propertyService.get(id);
        return "editProperty";
    }

    @Action("admin_property_delete")
    public String delete(){
        int id = property.getId();
        property= (Property) propertyService.get(id);
        propertyService.delete(property);
        return "listPropertyPage";
    }

    @Action("admin_property_update")
    public String update(){
        propertyService.update(property);
        return "listPropertyPage";
    }

    @Action("admin_property_add")
    public String add(){
        propertyService.save(property);
        return "listPropertyPage";
    }
}
