/**
 * ������
 * Copyright��Copyright ��c�� 2017
 * Company���Ͼ�����
 * @author jxgu 
 * @version 1.0 2017��9��24��
 * @see history
 * 2017��9��24��
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
 *         �๦��������
 *
 */
@Controller
// խ������ӳ�䣺Ϊ��ֹ��Ͷ�����controller��������ʱ�����������൱����url�м���һ��Ŀ¼����ֹ����
// ���統ǰlist�ķ���·�� localhost:8080/ssm_example/items/list.action
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
     * �����������������ĸ�������springmvcĬ�ϵĲ������ɼӿɲ��ӣ��Ƿ�ӿ���Ҫ
     * 
     * @param request
     * @param response
     * @param session
     * @param model
     * @return
     * @throws Exception
     * 
     *             ͨ��@PathVariable���Խ���url�д���Ĳ���
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
	    throw new CustomException("��Ʒ��Ϣ�����ڣ�");
	}

	// Model�б����˷���ҳ�������
	// Model�ײ���ʵ�õľ���request����������Ϣ�����Ƕ�request��������չ��
	model.addAttribute("item", item);

	// ���springmvc��������һ���򵥵��ַ�����Ĭ������ַ�����ҳ�������
	return "editItem";
    }

    // springmvc����ֱ�ӽ���pojo���ͣ�Ҫ��ҳ����input���name�������Ʊ������pojo����������
    @RequestMapping("/updateitem")
    public String updateItem(MultipartFile pictureFile, Items items,
	    Model model) throws Exception {

	// 1.��ȡͼƬ��������
	String picFilePath = pictureFile.getOriginalFilename();

	// 2.������ɵ��ַ���+ͼƬ����չ�������µ�ͼƬ���ƣ���ֹ����
	String newPicFileName = UUID.randomUUID()
		+ picFilePath.substring(picFilePath.indexOf("."));
	// 3.��ͼƬ���浽Ӳ��
	pictureFile.transferTo(
		new File("C:\\Users\\jxgu\\Pictures\\" + newPicFileName));

	// 4.��ͼƬ���Ʊ��浽���ݿ���
	items.setPic(newPicFileName);

	// editItem.jsp����Ʒ�������ڴ�ҳ���ϴ���������Ҫʹ���Զ���ת����GlobalString2DateConverter
	// ����Stringת��ΪDate����
	// items.setCreatetime(new Date());

	itemsService.updateItemsById(items);

	// �ض����������URL��ַ�����仯��request�е����ݲ����Դ����ض����ķ�����
	// return "success";
	// �ض������һ��д��
	// model.addAttribute("id", items.getId());
	// return "redirect:itemEdit.action";
	// return "redirect:itemEdit" + items.getId();

	// ����ת�����������URL��ַ�������仯��reuqest�е����ݿ��Դ����ض����ķ�����
	// model.addAttribute("id", items.getId());
	// springMVC������ת�������ص��ַ�����forward��ͷ��������ת��
	// ����forward��itemEdit.action�����·��
	// ����forward:/itemEdit.action·������/��ͷ��Ϊ����·��
	// return "forward:itemEdit.action";
	// return "forward:/items/itemEdit.action"
	return "forward:/items/itemEdit/" + items.getId();
    }

    // controller����ֵ����һ��д����һ�����֣�ModelAndView, String(�Ƽ�),
    // void���ƻ�springmvc�Ľṹ�����Ƽ�����
    // @RequestMapping("/updateitem")
    // public void updateItem(Items items, HttpServletRequest request,
    // HttpServletResponse response) throws Exception {
    //
    // itemsService.updateItemsById(items);
    //
    // // ���뷵��ҳ�������
    // // request.setAttribute("", arg1);
    //
    // // ָ�����ص�ҳ��,����springmvc�������Ҫдҳ�������·��
    // request.getRequestDispatcher("/WEB-INF/jsp/success.jsp")
    // .forward(request, response);
    //
    // }

    // ���controller�н��յ���VO��ҳ���Ͻ��յ�ֵ��vo������.����.����.
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

    // ����jackson��jar����controller�ķ����п���ʹ��@RequestBody,
    // ��springmvc��json��ʽ�ַ����Զ�ת����java�е�pojo����
    // ҳ��json��keyҪ����pojo����������
    // json����controller������void������Ҫstring�����᷵�ص���ǰҳ��
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
