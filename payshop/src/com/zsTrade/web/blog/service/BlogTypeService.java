//Powered By zsCat, Since 2016 - 2020

package com.zsTrade.web.blog.service;

import com.zsTrade.common.base.BaseService;
import com.zsTrade.web.blog.model.BlogType;

/**
* @author zsCat 2016-6-14 13:56:36
 * @Email: 951449465@qq.com
 * @version 4.0v
 *	我的blog
 */

public interface BlogTypeService extends BaseService<BlogType>{

	

	
	/**
	 * 保存或更新
	 * 
	 * @param BlogType
	 * @return
	 */
	public int saveBlogType(BlogType record) ;
	/**
	 * 删除
	* @param CmsArticle
	* @return
	 */
	public int deleteBlogType(BlogType record);


}
