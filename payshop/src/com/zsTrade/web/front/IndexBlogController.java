package com.zsTrade.web.front;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.zsTrade.common.constant.Constant;
import com.zsTrade.common.utils.PasswordEncoder;
import com.zsTrade.web.blog.model.Blog;
import com.zsTrade.web.blog.model.BlogTemplate;
import com.zsTrade.web.blog.model.BlogType;
import com.zsTrade.web.blog.model.Comment;
import com.zsTrade.web.blog.model.Link;
import com.zsTrade.web.blog.service.BlogService;
import com.zsTrade.web.blog.service.BlogTemplateService;
import com.zsTrade.web.blog.service.BlogTypeService;
import com.zsTrade.web.blog.service.CommentService;
import com.zsTrade.web.blog.service.LinkService;
import com.zsTrade.web.sys.model.SysUser;
import com.zsTrade.web.sys.service.SysUserService;
import com.zsTrade.web.sys.utils.SysUserUtils;
	/**
	 * 
	 * @author zs 2016-5-5 11:33:51
	 * @Email: 951449465@qq.com
	 * @version 4.0v
	 *	我的blog
	 */
@Controller
@RequestMapping("front/blog")
public class IndexBlogController {

	
	
	@Resource
	private BlogTemplateService BlogTemplateService;

	
		
	@Resource
	private BlogService blogService;
	@Resource
	private CommentService CommentService;
	@Resource
	private BlogTypeService BlogTypeService;
	@Resource
	private SysUserService SysUserService;
	@Resource
	private LinkService LinkService;
	
	
	
	/**
	 * 根据关键字查询相关博客信息
	 * @param q
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping("/q")
//	public ModelAndView search(@RequestParam(value="q",required=false)String q,@RequestParam(value="page",required=false)String page,HttpServletRequest request)throws Exception{
//		if(StringUtil.isEmpty(page)){
//			page="1";
//		}
//		ModelAndView mav=new ModelAndView();
//		mav.addObject("mainPage", "result");
//		List<Blog> blogList=blogIndex.searchBlog(q.trim());
//		Integer toIndex=blogList.size()>=Integer.parseInt(page)*10?Integer.parseInt(page)*10:blogList.size();
//		mav.addObject("blogList",blogList.subList((Integer.parseInt(page)-1)*10, toIndex));
//		mav.addObject("pageCode",this.genUpAndDownPageCode(Integer.parseInt(page), blogList.size(), q,10,request.getServletContext().getContextPath()));
//		mav.addObject("q",q);
//		mav.addObject("resultTotal",blogList.size());
//		mav.addObject("pageTitle","搜索关键字'"+q+"'结果页面_Java开源博客系统");
//		mav.setViewName("front/layout/mainTemp");
//		return mav;
//	}
	
	/**
	 * 获取下一篇博客和下一篇博客代码
	 * @param lastBlog
	 * @param nextBlog
	 * @return
	 */
	private String genUpAndDownPageCode(Blog lastBlog,Blog nextBlog,String projectContext){
		StringBuffer pageCode=new StringBuffer();
		if(lastBlog==null || lastBlog.getId()==null){
			pageCode.append("<p>上一篇：没有了</p>");
		}else{
			pageCode.append("<p>上一篇：<a href='"+projectContext+"/front/blog/articles/"+lastBlog.getId()+".html'>"+lastBlog.getTitle()+"</a></p>");
		}
		if(nextBlog==null || nextBlog.getId()==null){
			pageCode.append("<p>下一篇：没有了</p>");
		}else{
			pageCode.append("<p>下一篇：<a href='"+projectContext+"/front/blog/articles/"+nextBlog.getId()+".html'>"+nextBlog.getTitle()+"</a></p>");
		}
		return pageCode.toString();
	}
	
	/**
	 * 获取上一页，下一页代码 查询博客用到
	 * @param page 当前页
	 * @param totalNum 总记录数
	 * @param q 查询关键字
	 * @param pageSize 每页大小
	 * @param projectContext
	 * @return
	 */
	private String genUpAndDownPageCode(Integer page,Integer totalNum,String q,Integer pageSize,String projectContext){
		long totalPage=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
		StringBuffer pageCode=new StringBuffer();
		if(totalPage==0){
			return "";
		}else{
			pageCode.append("<nav>");
			pageCode.append("<ul class='pager' >");
			if(page>1){
				pageCode.append("<li><a href='"+projectContext+"/blog/q.html?page="+(page-1)+"&q="+q+"'>上一页</a></li>");
			}else{
				pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
			}
			if(page<totalPage){
				pageCode.append("<li><a href='"+projectContext+"/blog/q.html?page="+(page+1)+"&q="+q+"'>下一页</a></li>");				
			}else{
				pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");				
			}
			pageCode.append("</ul>");
			pageCode.append("</nav>");
		}
		return pageCode.toString();
	}
	
	/**
	 * 添加或者修改评论
	 * @param comment
	 * @param response
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping("/comment")
//	public String save(Comment comment,@RequestParam("imageCode") String imageCode,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
//		String sRand=(String) session.getAttribute("sRand"); // 获取系统生成的验证码
//		JSONObject result=new JSONObject();
//		int resultTotal=0; // 操作的记录条数
//		if(!imageCode.equals(sRand)){
//			result.put("success", false);
//			result.put("errorInfo", "验证码填写错误！");
//		}else{
//			String userIp=request.getRemoteAddr(); // 获取用户IP
//			comment.setUserIp(userIp);
//			if(comment.getId()==null){
//				resultTotal=commentService.add(comment);
//				// 该博客的回复次数加1
//				Blog blog=blogService.findById(comment.getBlog().getId());
//				blog.setReplyHit(blog.getReplyHit()+1);
//				blogService.update(blog);
//			}else{
//				
//			}
//			if(resultTotal>0){
//				result.put("success", true);
//			}else{
//				result.put("success", false);
//			}
//		}
//		ResponseUtil.write(response, result);
//		return null;
//	}
	
	/**
	 * 查找博主信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/aboutMe")
	public ModelAndView aboutMe()throws Exception{
		ModelAndView mav=new ModelAndView();
		mav.addObject("SysUser",SysUserService.selectByPrimaryKey(1L));
		mav.addObject("mainPage", "info");
		mav.addObject("pageTitle","关于博主_zsCat开源博客系统");
		mav.setViewName("front/layout/mainTemp");
		return mav;
	}
	/**
	 * 源码下载
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/download")
	public ModelAndView download()throws Exception{
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "download");
		mav.addObject("pageTitle","本站源码下载页面_Java开源博客系统");
		mav.setViewName("front/layout/mainTemp");
		return mav;
	}
	
	@RequestMapping("/indexns")
	public ModelAndView indexns(@RequestParam(value = "pageNum",required=false,defaultValue="1")Integer pageNum,
			@RequestParam(value = "pageSize",required=false,defaultValue="12")Integer pageSize)throws Exception{
		ModelAndView mav=new ModelAndView();
		List<Blog> blogList = blogService.select(null);
		
		mav.addObject("blogList", blogList);
		mav.setViewName("blog/indexns");
		return mav;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("/indexone")
	public ModelAndView index(@RequestParam(value = "pageNum",required=false,defaultValue="1")Integer pageNum,
			@RequestParam(value = "pageSize",required=false,defaultValue="12")Integer pageSize)throws Exception{
		ModelAndView mav=new ModelAndView();
		List<Blog> blogList = blogService.select(null);
		for(Blog blog:blogList){
			BlogType blogType=BlogTypeService.selectByPrimaryKey(blog.getTypeid());
			blog.setBlogType(blogType);
			String blogInfo=blog.getContent();
			Document doc=Jsoup.parse(blogInfo);
			Elements jpgs=doc.select("img[src]"); //　查找扩展名是jpg的图片
			if(jpgs!=null && jpgs.size()>0){
			Element jpg=jpgs.get(0);
				if(jpgs!=null && jpg!=null){
					String linkHref = jpg.attr("src");
					blog.setImg(linkHref);
				}else{
					blog.setImg("static/bbs/img/menu.png");
				}
			}
		}
		
		mav.addObject("blogList", blogList);
		//精心
		PageInfo<Blog> blogHotList = blogService.selectPage(1, 6, new Blog(),"clickHit desc");
		mav.addObject("blogHotList", blogHotList);
		//最新
		PageInfo<Blog> blogTopList = blogService.selectPage(1, 6, new Blog(),"releaseDate desc");
		mav.addObject("blogTopList", blogTopList);
		//随机
		PageInfo<Blog> blogRamList = blogService.selectPage(1, 6, new Blog(),"RAND()");
		mav.addObject("blogRamList", blogRamList);
		
		mav.setViewName("blog/indexone");
		return mav;
	}
	
	@RequestMapping("/about")
	public ModelAndView aboutMe(@RequestParam(value = "pageNum",required=false,defaultValue="1")Integer pageNum,
			@RequestParam(value = "pageSize",required=false,defaultValue="12")Integer pageSize)throws Exception{
		ModelAndView mav=new ModelAndView();
		List<Blog> blogList = blogService.select(null);
		
		mav.addObject("blogList", blogList);
		mav.setViewName("blog/about");
		return mav;
	}
	
	@RequestMapping("/shuo")
	public ModelAndView jilu(@RequestParam(value = "pageNum",required=false,defaultValue="1")Integer pageNum,
			@RequestParam(value = "pageSize",required=false,defaultValue="12")Integer pageSize)throws Exception{
		ModelAndView mav=new ModelAndView();
		List<Blog> blogList = blogService.select(null);
		
		mav.addObject("blogList", blogList);
		mav.setViewName("blog/shuo");
		return mav;
	}
	@RequestMapping("/daily")
	public ModelAndView daily(@RequestParam(value = "pageNum",required=false,defaultValue="1")Integer pageNum,
			@RequestParam(value = "pageSize",required=false,defaultValue="12")Integer pageSize)throws Exception{
		ModelAndView mav=new ModelAndView();
		List<Blog> blogList = blogService.select(null);
		
		mav.addObject("blogList", blogList);
		mav.setViewName("blog/daily");
		return mav;
	}
	@RequestMapping("/xc")
	public ModelAndView xc(@RequestParam(value = "pageNum",required=false,defaultValue="1")Integer pageNum,
			@RequestParam(value = "pageSize",required=false,defaultValue="12")Integer pageSize)throws Exception{
		ModelAndView mav=new ModelAndView();
//		PageInfo<CmsImg> imgList=CmsImgService.selectPage(pageNum, 10000, null);
//		mav.addObject("imgList", imgList.getList());
		mav.setViewName("blog/xc");
		return mav;
	}
	@RequestMapping("/guestbook")
	public ModelAndView guestbook(@RequestParam(value = "pageNum",required=false,defaultValue="1")Integer pageNum,
			@RequestParam(value = "pageSize",required=false,defaultValue="12")Integer pageSize)throws Exception{
		ModelAndView mav=new ModelAndView();
		List<Blog> blogList = blogService.select(null);
		
		mav.addObject("blogList", blogList);
		mav.setViewName("blog/guestbook");
		return mav;
	}
	
	
	//=====================草根
	
	@RequestMapping("/indexc")
	public ModelAndView indexc(@RequestParam(value = "pageNum",required=false,defaultValue="1")Integer pageNum,
			@RequestParam(value = "pageSize",required=false,defaultValue="12")Integer pageSize)throws Exception{
		ModelAndView mav=new ModelAndView();
		List<BlogTemplate> tempList=BlogTemplateService.select(null);
		mav.addObject("tempList", tempList);
		for(BlogTemplate temp:tempList){
		
			String tempInfo=temp.getContent();
			Document doc1=Jsoup.parse(tempInfo);
			Elements jpgs1=doc1.select("img[src]"); //　查找扩展名是jpg的图片
			if(jpgs1!=null && jpgs1.size()>0){
			Element jpg1=jpgs1.get(0);
				if(jpgs1!=null && jpg1!=null){
					String linkHref = jpg1.attr("src");
					temp.setImg(linkHref);
				}else{
					temp.setImg("static/bbs/img/menu.png");
				}
			}
		}
		List<Blog> blogList = blogService.select(null);
		for(Blog blog:blogList){
			BlogType blogType=BlogTypeService.selectByPrimaryKey(blog.getTypeid());
			blog.setBlogType(blogType);
			String blogInfo=blog.getContent();
			Document doc=Jsoup.parse(blogInfo);
			Elements jpgs=doc.select("img[src]"); //　查找扩展名是jpg的图片
			if(jpgs!=null && jpgs.size()>0){
			Element jpg=jpgs.get(0);
				if(jpgs!=null && jpg!=null){
					String linkHref = jpg.attr("src");
					blog.setImg(linkHref);
				}else{
					blog.setImg("static/bbs/img/menu.png");
				}
			}
		}
		
		mav.addObject("blogList", blogList);
		mav.setViewName("blog/caogen/index");
		return mav;
	}
	@RequestMapping("/book")
	public ModelAndView book(@RequestParam(value = "pageNum",required=false,defaultValue="1")Integer pageNum,
			@RequestParam(value = "pageSize",required=false,defaultValue="12")Integer pageSize)throws Exception{
		ModelAndView mav=new ModelAndView();
		
		mav.setViewName("blog/caogen/caselist");
		return mav;
	}
	@RequestMapping("/aboutc")
	public ModelAndView aboutc(@RequestParam(value = "pageNum",required=false,defaultValue="1")Integer pageNum,
			@RequestParam(value = "pageSize",required=false,defaultValue="12")Integer pageSize)throws Exception{
		ModelAndView mav=new ModelAndView();
		
		mav.setViewName("blog/caogen/about");
		return mav;
	}
	@RequestMapping("/knowledge")
	public ModelAndView knowledge(@RequestParam(value = "pageNum",required=false,defaultValue="1")Integer pageNum,
			@RequestParam(value = "pageSize",required=false,defaultValue="12")Integer pageSize)throws Exception{
		ModelAndView mav=new ModelAndView();
		
		mav.setViewName("blog/caogen/knowledge");
		return mav;
	}
	@RequestMapping("/moodlist")
	public ModelAndView moodlist(@RequestParam(value = "pageNum",required=false,defaultValue="1")Integer pageNum,
			@RequestParam(value = "pageSize",required=false,defaultValue="12")Integer pageSize)throws Exception{
		ModelAndView mav=new ModelAndView();
		
		mav.setViewName("blog/caogen/moodlist");
		return mav;
	}
	@RequestMapping("/news/{id}")
	public ModelAndView news(@PathVariable("id") Long id)throws Exception{
		ModelAndView mav=new ModelAndView();
		Blog b=blogService.selectByPrimaryKey(id);
		mav.addObject("blog", b);
		mav.setViewName("blog/caogen/new");
		return mav;
	}
	@RequestMapping("/newlist")
	public ModelAndView newlist(@RequestParam(value = "pageNum",required=false,defaultValue="1")Integer pageNum,
			@RequestParam(value = "pageSize",required=false,defaultValue="12")Integer pageSize)throws Exception{
		ModelAndView mav=new ModelAndView();
		PageInfo<Blog> blogList = blogService.selectPage(pageNum, 10000, new Blog());
		for(Blog blog:blogList.getList()){
			String blogInfo=blog.getContent();
			Document doc=Jsoup.parse(blogInfo);
			Elements jpgs=doc.select("img[src]"); //　查找扩展名是jpg的图片
			if(jpgs!=null && jpgs.size()>0){
			Element jpg=jpgs.get(0);
				if(jpgs!=null && jpg!=null){
					String linkHref = jpg.attr("src");
					blog.setImg(linkHref);
				}else{
					blog.setImg("static/bbs/img/menu.png");
				}
			}
		}
		mav.addObject("page", blogList);
		mav.setViewName("blog/caogen/newlist");
		return mav;
	}
	@RequestMapping("/share")
	public ModelAndView share(@RequestParam(value = "pageNum",required=false,defaultValue="1")Integer pageNum,
			@RequestParam(value = "pageSize",required=false,defaultValue="12")Integer pageSize)throws Exception{
		ModelAndView mav=new ModelAndView();
		PageInfo<Blog> blogList = blogService.selectPage(pageNum, 10000, new Blog());
		mav.addObject("page", blogList);
		mav.setViewName("blog/caogen/share");
		return mav;
	}
	@RequestMapping("/template/{id}")
	public ModelAndView template(@PathVariable("id") Long id)throws Exception{
		ModelAndView mav=new ModelAndView();
		Blog b=blogService.selectByPrimaryKey(id);
		mav.addObject("template", b);
		mav.setViewName("blog/caogen/template");
		return mav;
	}
	
	
	
	//============================================社区==================
	@RequestMapping("/index")
	public ModelAndView indexs(@RequestParam(value = "pageNum",required=false,defaultValue="1")Integer pageNum,
			@RequestParam(value = "pageSize",required=false,defaultValue="12")Integer pageSize)throws Exception{
		ModelAndView mav=new ModelAndView();
		List<Blog> blogList = blogService.select(null);
		
				//最新
				PageInfo<Blog> blogTopList = blogService.selectPage(1, 12, new Blog(),"releaseDate desc");
				mav.addObject("blogTopList", blogTopList);
				//随机
				PageInfo<Blog> blogRamList = blogService.selectPage(1, 12, new Blog(),"RAND()");
				mav.addObject("blogRamList", blogRamList);
				mav.addObject("userList", SysUserService.selectPage(1, 12, new SysUser()));
				
				List<BlogType> typeList=BlogTypeService.select(new BlogType());
				mav.addObject("typeList", typeList);
				
				List<Link> linkList=LinkService.select(new Link());
				mav.addObject("linkList", linkList);
				mav.setViewName("blog/community/index");
				return  mav;
		
	}
	// 首页精品课程、最新课程、全部课程
			@RequestMapping("/ajax/bna")
		public String queryCourse(HttpServletRequest request) {
				try {
					String id = request.getParameter("order");
					if (id != null && !id.equals("")) {
						Blog a=new Blog();
						a.setTypeid(Long.parseLong(id));
						// 获得精品课程、最新课程、全部课程
						List<Blog> artList = blogService.select(a);
						request.setAttribute("artList", artList);
					}
				} catch (Exception e) {
//					logger.error("WebFrontController.queryCourse", e);
//					return setExceptionRequest(request, e);
				}
				return "blog/community/ajax-index";
			}
	@RequestMapping("/indexs1")
	public ModelAndView indexs1(@RequestParam(value = "pageNum",required=false,defaultValue="1")Integer pageNum,
			@RequestParam(value = "pageSize",required=false,defaultValue="12")Integer pageSize)throws Exception{
		ModelAndView mav=new ModelAndView();
		Blog blog1=new Blog();
		blog1.setTypeid(3L);
		List<Blog> blogList = blogService.select(blog1);
		for(Blog blog:blogList){
			BlogType blogType=BlogTypeService.selectByPrimaryKey(blog.getTypeid());
			blog.setBlogType(blogType);
			String blogInfo=blog.getContent();
			Document doc=Jsoup.parse(blogInfo);
			Elements jpgs=doc.select("img[src]"); //　查找扩展名是jpg的图片
			if(jpgs!=null && jpgs.size()>0){
			Element jpg=jpgs.get(0);
				if(jpgs!=null && jpg!=null){
					String linkHref = jpg.attr("src");
					blog.setImg(linkHref);
				}else{
					blog.setImg("static/bbs/img/menu.png");
				}
			}
		}
			mav.addObject("blogList", blogList);
				//最新
				PageInfo<Blog> blogTopList = blogService.selectPage(1, 12, new Blog(),"releaseDate desc");
				mav.addObject("blogTopList", blogTopList);
				//随机
				PageInfo<Blog> blogRamList = blogService.selectPage(1, 12, new Blog(),"RAND()");
				mav.addObject("blogRamList", blogRamList);
				mav.addObject("userList", SysUserService.selectPage(1, 12, new SysUser()));
				
				List<BlogType> typeList=BlogTypeService.select(new BlogType());
				mav.addObject("typeList", typeList);
				
				List<Link> linkList=LinkService.select(new Link());
				mav.addObject("linkList", linkList);
				mav.setViewName("blog/community/index1");
				return  mav;
		
	}
	
	@RequestMapping("/blogListByType/{id}")
	public ModelAndView blogListByType(@PathVariable("id") Long id)throws Exception{
		ModelAndView mav=new ModelAndView();
		Blog blog1=new Blog();
		blog1.setTypeid(id);
		List<Blog> blogList = blogService.select(blog1);
		for(Blog blog:blogList){
			BlogType blogType=BlogTypeService.selectByPrimaryKey(blog.getTypeid());
			blog.setBlogType(blogType);
			String blogInfo=blog.getContent();
			Document doc=Jsoup.parse(blogInfo);
			Elements jpgs=doc.select("img[src]"); //　查找扩展名是jpg的图片
			if(jpgs!=null && jpgs.size()>0){
			Element jpg=jpgs.get(0);
				if(jpgs!=null && jpg!=null){
					String linkHref = jpg.attr("src");
					blog.setImg(linkHref);
				}else{
					blog.setImg("static/bbs/img/menu.png");
				}
			}
		}
			mav.addObject("blogList", blogList);
				//最新
				PageInfo<Blog> blogTopList = blogService.selectPage(1, 12, new Blog(),"releaseDate desc");
				mav.addObject("blogTopList", blogTopList);
				//随机
				PageInfo<Blog> blogRamList = blogService.selectPage(1, 12, new Blog(),"RAND()");
				mav.addObject("blogRamList", blogRamList);
				mav.addObject("userList", SysUserService.selectPage(1, 12, new SysUser()));
				
				List<BlogType> typeList=BlogTypeService.select(new BlogType());
				mav.addObject("typeList", typeList);
				List<Link> linkList=LinkService.select(new Link());
				mav.addObject("linkList", linkList);
				mav.setViewName("blog/community/index");
				return  mav;
		
	}
	@RequestMapping("/shequDetail/{id}")
	public ModelAndView shequDetail(@PathVariable("id") Long id)throws Exception{
		ModelAndView mav=new ModelAndView();
		
		//最新
		PageInfo<Blog> blogTopList = blogService.selectPage(1, 12, new Blog(),"releaseDate desc");
		mav.addObject("blogTopList", blogTopList);
		//随机
		PageInfo<Blog> blogRamList = blogService.selectPage(1, 12, new Blog(),"RAND()");
		mav.addObject("blogRamList", blogRamList);
		
		mav.addObject("userList", SysUserService.selectPage(1, 12, new SysUser()));
		Blog b=blogService.selectByPrimaryKey(id);
		mav.addObject("blog", b);
		
		Comment comment=new Comment();
		comment.setBlogid(id);
		PageInfo<Comment> commList=CommentService.selectPage(1, 15, comment,"commentdate desc");
		mav.addObject("commList", commList);
		if(b.getTypeid()==4){
			mav.setViewName("blog/community/jie/detail2");
		}else{
			mav.setViewName("blog/community/jie/detail");
		}
		
		return mav;
	}
	
	@RequestMapping("/home/{id}")
	public ModelAndView home(@PathVariable("id") Long id)throws Exception{
		ModelAndView mav=new ModelAndView();
		Blog blog=new Blog();
		blog.setBloggerId(id);
		PageInfo<Blog> page=blogService.selectPage(1, 15, blog);
		mav.addObject("page", page);
		
		Comment comment=new Comment();
		comment.setBloggerId(id);
		PageInfo<Comment> commList=CommentService.selectPage(1, 15, comment);
		mav.addObject("commList", commList);
		mav.setViewName("blog/community/user/home");
		return mav;
	
	}
	 /**
	 * 跳转到登录页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String toLogin() {
		if( SysUserUtils.getSessionLoginUser()!= null){
			return "redirect:/front/blog/index";
		}
		return "/blog/community/user/login";
	}
	
	/**
	 * 登录验证
	 * 
	 * @param username
	 * @param password
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> checkLogin(String username,
			String password,String vercode,  HttpServletRequest request) {

		Map<String, Object> msg = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		username = StringUtils.trim(username);
		password = StringUtils.trim(password);
		if (!StringUtils.equalsIgnoreCase("3711111", vercode)) {
			msg.put("error", "验证码错误!");
			return msg;
		}
		SysUser user = SysUserService.checkUser(username, password);
		if (null != user) {
			session.setAttribute(Constant.SESSION_LOGIN_USER, user);
		} else {
			msg.put("error", "用户名或密码错误");
		}
		return msg;
	}
 
	 /**
	 * 跳转到登录页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "reg", method = RequestMethod.GET)
	public String reg() {
		if( SysUserUtils.getSessionLoginUser() != null){
			return "redirect:/front/blog/index";
		}
		return "/blog/community/user/reg";
	}

	@RequestMapping(value = "reg", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> reg(
			@RequestParam(value = "password",required=true)String  password,
			@RequestParam(value = "username",required=false)String username,
			@RequestParam(value = "nickname",required=false)String nickname,
			@RequestParam(value = "passwordRepeat",required=true)String passwordRepeat,
			@RequestParam(value = "vercode",required=true)String vercode,HttpServletRequest request) {
		Map<String, Object> msg = new HashMap<String, Object>();
		if (!StringUtils.equalsIgnoreCase("3711111", vercode)) {
			msg.put("error", "验证码错误!");
			return msg;
		}
		if (!StringUtils.equalsIgnoreCase(password, passwordRepeat)) {
			msg.put("error", "密码不一致!");
			return msg;
		}
		String secPwd = null ;
		SysUser m=new SysUser();
			secPwd = PasswordEncoder.encrypt(password, username);
		    m.setPassword(secPwd);
		    m.setUsername(username);
		    m.setName(nickname);
		    m.setCompanyId(50L);m.setOfficeId(50L);
		try {
			int result = SysUserService.insertSelective(m);
			HttpSession session = request.getSession();
			if (result>0) {
				session.setAttribute(Constant.SESSION_LOGIN_USER, m);
			} else {
				msg.put("error", "注册失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
 	/**
	 * 用户退出
	 * 
	 * @return 跳转到登录页面
	 */
	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/front/blog/login";
	}

}
