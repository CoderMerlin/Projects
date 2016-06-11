package com.yc.hdmedia.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yc.hdmedia.entity.Author;

@Service("authorMapper")
public interface AuthorMapper {
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
	public List<Author> findAllAuthor(Map<String,Object> params);
	
	public List<Author> findById(int author_id);
	
	public List<Author> findAuthor_jxdsh();
}
