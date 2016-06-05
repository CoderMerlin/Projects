package com.yc.hdmedia.mapper;

import java.util.List;
import java.util.Map;

import com.yc.hdmedia.entity.GongGao;

public interface GongGaoMapper {
	public int addGongGao(GongGao gonggao); //��ӹ���
	
	public int updateGongGao(GongGao gonggao); //�޸Ĺ���
	
	public int del(int gid);//ɾ��
	
	public int total();//�ܼ�¼��
	
	/**
	 * ��ҳ��ѯ������Ϣ
	 * @param pageNo��Ҫ��ѯ��ҳ�������Ϊnull�����ѯȫ��
	 * @param pageSize��ҳ����ʾ������
	 * @return
	 */
	public List<GongGao> getAllGongGao(Map<String,Object> params);
	
	/**
	 * ��ѯָ���Ĺ�����Ϣ
	 * @param gid��Ҫ��ѯ�Ĺ�����
	 * @return
	 */
	public GongGao findGongGaoByGid(int gid);
}
