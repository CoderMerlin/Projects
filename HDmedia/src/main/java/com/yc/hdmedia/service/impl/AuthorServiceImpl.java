package com.yc.hdmedia.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yc.hdmedia.entity.Author;
import com.yc.hdmedia.mapper.AuthorMapper;
import com.yc.hdmedia.service.AuthorService;

@Service("authorService")
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorMapper authorMapper;
	public int addAuthor(Author author) {
		return authorMapper.addAuthor(author);
	}

	public int updateAuthor(Author author) {
		return authorMapper.updateAuthor(author);
	}

	public int delAuthor(String author_ids) {
		int result=0;
		if(author_ids.contains(",")){
			String[] str=author_ids.split(",");
			for(String author_id:str){
				result+=authorMapper.delAuthor(author_id);
			}
		}else{
			String author_id=author_ids;
			result=authorMapper.delAuthor(author_id);
		}
		return result;
	}

	public int total() {
		return authorMapper.total();
	}

	public List<Author> findAllAuthor(Integer pageNo, Integer pageSize) {
		Map<String , Object> params=new HashMap<String , Object>();
		params.put("pageNo", pageNo*pageSize);
		params.put("pageSize", (pageNo-1)*pageSize);
		return authorMapper.findAllAuthor(params);
	}

	public List<Author> findById(int author_id) {
		return authorMapper.findById(author_id);
	}

	@Override
	public List<Author> findAuthor_jxdsh() {
		return null;
	}

	/*public List<Author> findAuthor_jxdsh() {
		String sql="select * from (select a.*,rownum rn from (select * from author where status=1 order by author_weight desc)a where rownum<=2)b where rn>0";
		return 3;
	}*/
}
