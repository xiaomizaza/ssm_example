/**
 * 描述：
 * Copyright：Copyright （c） 2017
 * Company：南京银行
 * @author jxgu 
 * @version 1.0 2017年9月24日
 * @see history
 * 2017年9月24日
 */
package com.jxgu.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jxgu.customexception.CustomException;
import com.jxgu.pojo.Items;
import com.jxgu.service.ItemsService;
import com.jxgu.vo.QueryVo;

import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

/**
 * @author jxgu
 *
 *         类功能描述：
 *
 */
@Controller
// 窄化请求映射：为防止你和队友在controller方法起名时崇明，所以相当于在url中加了一层目录，防止重名
// 例如当前list的访问路径 localhost:8080/ssm_example/items/list.action
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    @Autowired
    private Pool<Jedis> jedisPool;

    @RequestMapping("/list")
    public ModelAndView itemList() throws Exception {

	List<Items> list = itemsService.list();

	ModelAndView modelAndView = new ModelAndView();
	modelAndView.addObject("itemList", list);
	modelAndView.setViewName("/itemList");

	return modelAndView;
    }

    /**
     * 
     * 方法功能描述：这四个参数是springmvc默认的参数，可加可不加，是否加看需要
     * 
     * @param request
     * @param response
     * @param session
     * @param model
     * @return
     * @throws Exception
     * 
     *             通过@PathVariable可以接受url中传入的参数
     */
    // @RequestMapping("/itemEdit")
    // public String itemEdit(HttpServletRequest request,
    @RequestMapping("/itemEdit/{id}")
    public String itemEdit(@PathVariable("id") Integer id,
	    HttpServletResponse response, HttpSession session, Model model)
	    throws Exception {

	// String idStr = request.getParameter("id");
	// Integer id = Integer.parseInt(idStr);

	Items item = itemsService.findItemsById(id);
	if (null == item) {
	    throw new CustomException("商品信息不存在！");
	}

	// Model中保存了返回页面的数据
	// Model底层其实用的就是request域来传递信息，但是对request进行了扩展。
	model.addAttribute("item", item);

	// 如果springmvc方法返回一个简单的字符串，默认这个字符串是页面的名字
	return "editItem";
    }

    // springmvc可以直接接收pojo类型，要求页面上input框的name属性名称必须等于pojo的属性名称
    @RequestMapping("/updateitem")
    public String updateItem(MultipartFile pictureFile, Items items,
	    Model model) throws Exception {

	// 1.获取图片完整名称
	String picFilePath = pictureFile.getOriginalFilename();

	// 2.随机生成的字符串+图片的扩展名生成新的图片名称，防止重名
	String newPicFileName = UUID.randomUUID()
		+ picFilePath.substring(picFilePath.indexOf("."));
	// 3.将图片保存到硬盘
	pictureFile.transferTo(
		new File("C:\\Users\\jxgu\\Pictures\\" + newPicFileName));

	// 4.将图片名称保存到数据库中
	items.setPic(newPicFileName);

	// editItem.jsp中商品生产日期从页面上传过来，需要使用自定义转换器GlobalString2DateConverter
	// 来讲String转换为Date类型
	// items.setCreatetime(new Date());

	itemsService.updateItemsById(items);

	// 重定向：浏览器中URL地址发生变化，request中的数据不可以带到重定向后的方法中
	// return "success";
	// 重定向的另一种写法
	// model.addAttribute("id", items.getId());
	// return "redirect:itemEdit.action";
	// return "redirect:itemEdit" + items.getId();

	// 请求转发：浏览器中URL地址不发生变化，reuqest中的数据可以带到重定向后的方法中
	// model.addAttribute("id", items.getId());
	// springMVC中请求转发：返回的字符串以forward开头都是请求转发
	// 后面forward的itemEdit.action是相对路径
	// 后面forward:/itemEdit.action路径中以/开头的为绝对路径
	// return "forward:itemEdit.action";
	// return "forward:/items/itemEdit.action"
	return "forward:/items/itemEdit/" + items.getId();
    }

    // controller返回值的另一种写法（一共三种，ModelAndView, String(推荐),
    // void（破坏springmvc的结构，不推荐））
    // @RequestMapping("/updateitem")
    // public void updateItem(Items items, HttpServletRequest request,
    // HttpServletResponse response) throws Exception {
    //
    // itemsService.updateItemsById(items);
    //
    // // 塞入返回页面的数据
    // // request.setAttribute("", arg1);
    //
    // // 指定返回的页面,不走springmvc组件，需要写页面的完整路径
    // request.getRequestDispatcher("/WEB-INF/jsp/success.jsp")
    // .forward(request, response);
    //
    // }

    // 如果controller中接收的是VO，页面上接收的值是vo的属性.属性.属性.
    @RequestMapping("/search")
    public String search(QueryVo vo) throws Exception {

	System.out.println(vo);

	return "";
    }

    @RequestMapping("/delAll")
    public String delAll(QueryVo vo) throws Exception {
	System.out.println(vo);

	return "";
    }

    @RequestMapping("/updateAll")
    public String updateAll(QueryVo vo) throws Exception {
	System.out.println(vo);

	return "";
    }

    // 导入jackson的jar包在controller的方法中可以使用@RequestBody,
    // 让springmvc将json格式字符串自动转换成java中的pojo对象
    // 页面json的key要等于pojo的属性名称
    // json进入controller方法用void，不需要string，还会返回到当前页面
    // @RequestMapping("/sendJson")
    // public void json(@RequestBody Items items) throws Exception {
    // System.out.println(items);
    //
    // }
    @RequestMapping("/sendJson")
    @ResponseBody
    public Items json(@RequestBody Items items) throws Exception {
	System.out.println(items);
	return items;
    }

    @RequestMapping("/getRedis")
    public Items getRedis(@RequestBody Items items, HttpServletRequest request)
	    throws Exception {

	Jedis jedis = null;
	try {
	    jedis = jedisPool.getResource();
	    jedis.set("name", "zhaoerqing");

	    String name = jedis.get("name");
	    System.out.println(name);
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (null != jedis) {
		jedis.close();
	    }
	}
	return items;
    }
}
