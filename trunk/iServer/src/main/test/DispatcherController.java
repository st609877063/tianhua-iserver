

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.platform.domain.User;

@Controller
@RequestMapping("/test")
public class DispatcherController {
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getUsers(HttpServletResponse response,HttpServletRequest request,Model model) {
		String view = request.getParameter("view");
		if(view==null) view="jsp";
		Map map =new HashMap();
		User user1 = new User();
		//user1.setUserId("id1");
		user1.setName("name1");
		User user2 = new User();
		//user2.setUserId("id2");
		user2.setName("name2");
		List l = new ArrayList();
		l.add(user1);
		l.add(user2);
		map.put("list", l);
		return new ModelAndView("user_"+view,map);
	}
}
