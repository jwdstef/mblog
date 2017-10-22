/**
 * 
 */
package mblog.velocity.directive;

import mblog.base.context.SpringContextHolder;
import mblog.core.biz.PostBiz;
import mblog.core.data.Post;
import mblog.velocity.BaseDirective;
import mblog.velocity.handler.RenderHandler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.ServletRequest;
import java.io.IOException;

/**
 * 根据作者取文章列表
 * 
 * @author langhsu
 *
 */
public class AuthorContentsDirective extends BaseDirective {
	private PostBiz postPlanet;
	
	@Override
	public void initBean() {
		postPlanet = SpringContextHolder.getBean(PostBiz.class);
	}

	@Override
	public boolean render(RenderHandler handler) throws RuntimeException, IOException {
		ServletRequest request = handler.getRequest();
		
		// request 获取参数
        int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
        
        // 标签中获取参数
        long uid = handler.getIntParameter(0);
        String alias = handler.getStringParameter(1);

		Pageable pageable = new PageRequest(pn - 1, 10);
		Page<Post> result = postPlanet.pagingByAuthorId(pageable, uid);
		
		handler.put(alias, result);
		handler.doRender();
		
		postRender(handler.getContext());
		return true;
	}

	@Override
	public String getName() {
		return "author_contents";
	}

	@Override
	public int getType() {
		return BLOCK;
	}

}
