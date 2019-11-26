package com.cex.tmall.action;

import com.cex.tmall.pojo.Category;
import com.cex.tmall.pojo.Product;
import com.cex.tmall.service.ProductService;
import com.cex.tmall.util.Page;
import org.apache.struts2.convention.annotation.Action;

public class ProductAction extends Action4Result {
    @Action("admin_product_list")
    public String list(){
        if(page==null) page = new Page();
        int total = productService.total(category);
        page.setTotal(total);
        page.setCount(4);
        page.setParam("&category.id="+category.getId());
        category= (Category) categoryService.get(category.getId());
        products=productService.list(page,category);
        for (Product product : products) {
            productImageService.setFirstProductImage(product);
        }
        return "listProduct";
    }

    @Action("admin_product_edit")
    public String edit(){
        int id = product.getId();
        product= (Product) productService.get(id);
        return "editProduct";
    }

    @Action("admin_product_update")
    public String update(){
        productService.update(product);
        return "listProductPage";
    }

    @Action("admin_product_delete")
    public String delete(){
        int id = product.getId();
        product= (Product) productService.get(id);
        productService.delete(product);
        return "listProductPage";
    }

    @Action("admin_product_add")
    public String add(){
        productService.save(product);
        return "listProductPage";
    }
}
