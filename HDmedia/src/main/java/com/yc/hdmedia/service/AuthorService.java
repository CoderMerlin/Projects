package com.yc.hdmedia.service;

import java.util.List;
import com.yc.hdmedia.entity.Author;


public interface AuthorService {
	/**
	 * 添加
	 * @param authorDao
	 * @return
	 */
	public int addAuthor(Author author);
	/**
	 * 修改
	 * @param authorDao
	 * @return
	 */
	public int updateAuthor(Author author);
	/**
	 * 删除
	 * @param authorDao
	 * @return
	 */
	public int delAuthor(String author_id);
	/**
	 * 信息总记录数
	 * @param authorDao
	 * @return
	 */
	public int total();
	/**
	 * 分页查询作者信息
	 * @param pageNo：要查询的页数，如果为null，则查询全部
	 * @param pageSize：页面显示的条数
	 * @return
	 */
	public List<Author> findAllAuthor(Integer pageNo,Integer pageSize);
	
	public List<Author> findById(int author_id);
	
	public List<Author> findAuthor_jxdsh();
}
