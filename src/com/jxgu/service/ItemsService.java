/**
 * ������
 * Copyright��Copyright ��c�� 2017
 * Company���Ͼ�����
 * @author jxgu 
 * @version 1.0 2017��9��24��
 * @see history
 * 2017��9��24��
 */
package com.jxgu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jxgu.pojo.Items;

/**
 * @author jxgu
 *
 *         �๦��������
 *
 */
@Service
public interface ItemsService {

    public List<Items> list() throws Exception;

    public Items findItemsById(Integer id) throws Exception;

    public void updateItemsById(Items items) throws Exception;

}
