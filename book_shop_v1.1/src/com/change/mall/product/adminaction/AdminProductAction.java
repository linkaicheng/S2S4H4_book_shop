package com.change.mall.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.change.mall.categorysecond.service.CategorySecondService;
import com.change.mall.categorysecond.vo.CategorySecond;
import com.change.mall.product.service.ProductService;
import com.change.mall.product.vo.Product;
import com.change.mall.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台商品管理的Action
 * 
 * 
 */
public class AdminProductAction extends ActionSupport implements ModelDriven<Product> {
	// 模型驱动使用的对象
	private Product product = new Product();

	public Product getModel() {
		return product;
	}

	// 接收page参数
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	// 注入ProductService
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	// 注入CategorySecondService
	private CategorySecondService categorySecondService;

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	// 文件上传需要的三个属性:
	private File upload;
	private String uploadFileName;
	private String uploadContentType;

	public File getUpload() {
		return upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	// 查询所有的商品:
	public String findAll() {
		PageBean<Product> pageBean = productService.findByPage(page);
		// 将PageBean数据存入到值栈中.
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		// 页面跳转
		return "findAll";
	}

	// 跳转到添加页面的方法:
	public String addPage() {
		// 查询所有的二级分类:
		List<CategorySecond> csList = categorySecondService.findAll();
		// 将二级分类的数据显示到页面上
		ActionContext.getContext().getValueStack().set("csList", csList);
		// 页面跳转
		return "addPageSuccess";
	}

	// 保存商品的方法:
	public String save() throws IOException {
		// 将提交的数据添加到数据库中.
		product.setPdate(new Date());
		// product.setImage(image);
		if (upload != null) {
			// 将商品图片上传到服务器上.
			// 获得上传图片的服务器端路径.
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("config.properties");
			Properties properties = new Properties();
			properties.load(inputStream);
			String path = properties.getProperty("uploadProductImgPath");
			// 创建文件类型对象:
			File diskFile = new File(path + "\\products\\" + uploadFileName);
			// 文件上传:
			FileUtils.copyFile(upload, diskFile);
			// 也保存一份到服务器
			path = ServletActionContext.getServletContext().getRealPath("/products");
			// 创建文件类型对象:
			File diskFile2 = new File(path + "//" + uploadFileName);
			// 文件上传:
			FileUtils.copyFile(upload, diskFile2);
			product.setImage("products/" + uploadFileName);
		}
		productService.save(product);
		return "saveSuccess";
	}

	// 删除商品的方法:
	public String delete() {
		// 根据id查询商品信息
		product = productService.findByPid(product.getPid());
		// 删除商品的图片:
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("config.properties");
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String path = properties.getProperty("uploadProductImgPath");
		// 创建文件类型对象:
		File file = new File(path + "\\" + product.getImage());
		file.delete();
		// 删除数据库中商品记录:
		productService.delete(product);
		// 页面跳转
		return "deleteSuccess";
	}

	// 编辑商品的方法
	public String edit() {
		// 根据商品id查询商品信息
		product = productService.findByPid(product.getPid());
		// 查询所有二级分类
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		// 页面跳转到编辑页面:
		return "editSuccess";
	}

	// 修改商品的方法
	public String update() throws IOException {
		// 将信息修改到数据库
		product.setPdate(new Date());
		// 上传:
		if (upload != null) {
			// 删除商品的图片:
			InputStream is = this.getClass().getClassLoader().getResourceAsStream("config.properties");
			Properties pro = new Properties();
			try {
				pro.load(is);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String pa = pro.getProperty("uploadProductImgPath");
			// 创建文件类型对象:
			File file = new File(pa + "\\" + product.getImage());
			file.delete();

			// String delPath =
			// ServletActionContext.getServletContext().getRealPath("/" +
			// product.getImage());
			// File file = new File(delPath);
			// file.delete();
			// 获得上传图片的服务器端路径.如果路径选择的是servletContext.getRealPath("/files/" +
			// FileName)的方式，会把上传的文件保存到服务器路径下，
			// 每次重器服务器都会导致，文件被删除，为了方便测试，这里使用的是服务器外的一个磁盘路径
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("config.properties");
			Properties properties = new Properties();
			properties.load(inputStream);
			String path = properties.getProperty("uploadProductImgPath");
			// 创建文件类型对象:
			File diskFile = new File(path + "\\products\\" + uploadFileName);
			// 文件上传:
			FileUtils.copyFile(upload, diskFile);
			// 也保存一份到服务器
			path = ServletActionContext.getServletContext().getRealPath("/products");
			// 创建文件类型对象:
			File diskFile2 = new File(path + "//" + uploadFileName);
			// 文件上传:
			FileUtils.copyFile(upload, diskFile2);

			product.setImage("products/" + uploadFileName);

		}
		productService.update(product);
		// 页面跳转
		return "updateSuccess";

	}
}
