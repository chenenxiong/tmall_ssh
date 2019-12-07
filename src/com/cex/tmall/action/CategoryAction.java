package com.cex.tmall.action;

import com.cex.tmall.pojo.Product;
import com.cex.tmall.util.ImageUtil;
import com.cex.tmall.util.Page;
import com.cex.tmall.pojo.Category;
import com.cex.tmall.service.CategoryService;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import javax.persistence.Transient;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class CategoryAction extends Action4Result{


    @Action("admin_category_list")
    public String list(){
        if(page == null) page=new Page();
        int total = categoryService.total();
        page.setTotal(total);
        page.setCount(4);
        categorys = categoryService.listByPage(page);
        return "listCategory";
    }

    @Action("admin_category_update")
    public String update(){
        categoryService.update(category);
        if(null!=img){
            File imageFolder = new File(ServletActionContext.getServletContext().getRealPath("/img/category"));
            File file = new File(imageFolder,category.getId()+".jpg");
            try {
                FileUtils.copyFile(img,file);
                BufferedImage img = ImageUtil.change2jpg(file);
                ImageIO.write(img,"jpg",file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "listCategoryPage";
    }
    @Action("admin_category_edit")
    public String edit(){
        int id = category.getId();
        category = (Category) categoryService.get(id);
        return "editCategory";
    }
    @Action("admin_category_delete")
    public String delete(){
        categoryService.delete(category);
        return "listCategoryPage";
    }
    @Action("admin_category_add")
    public String add(){
        System.out.println("save : " + category.getName());
        categoryService.save(category);
        File imageFolder=new File(ServletActionContext.getServletContext().getRealPath("/img/category"));
        File file = new File(imageFolder,category.getId()+".jpg");
        System.out.println(file.getAbsolutePath());
        try {
            FileUtils.copyFile(img,file);
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img, "jpg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "listCategoryPage";
    }
}
