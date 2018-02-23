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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxgu.dao.ItemsMapper;
import com.jxgu.pojo.Items;
import com.jxgu.pojo.ItemsExample;

/**
 * @author jxgu
 *
 *         �๦��������
 *
 */
@Service
public class ItemServiceImpl implements ItemsService {

    @Autowired
    private ItemsMapper itemsMapper;

    @Override
    public List<Items> list() throws Exception {

	// ����Ҫ��ѯ�����Ļ���ֱ��new ItemsExample()
	ItemsExample example = new ItemsExample();
	List<Items> list = itemsMapper.selectByExampleWithBLOBs(example);

	return list;
    }

    @Override
    public Items findItemsById(Integer id) throws Exception {
	Items item = itemsMapper.selectByPrimaryKey(id);
	return item;
    }

    @Override
    public void updateItemsById(Items items) throws Exception {
	itemsMapper.updateByPrimaryKeyWithBLOBs(items);
    }

}
