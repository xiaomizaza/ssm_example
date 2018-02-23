/**
 * 描述：
 * Copyright：Copyright （c） 2017
 * Company：南京银行
 * @author jxgu 
 * @version 1.0 2017年9月24日
 * @see history
 * 2017年9月24日
 */
package com.jxgu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jxgu.pojo.Items;

/**
 * @author jxgu
 *
 *         类功能描述：
 *
 */
@Service
public interface ItemsService {

    public List<Items> list() throws Exception;

    public Items findItemsById(Integer id) throws Exception;

    public void updateItemsById(Items items) throws Exception;

}
