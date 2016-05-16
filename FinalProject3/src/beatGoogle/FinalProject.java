package beatGoogle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FinalProject
 */
@WebServlet("/FinalProject")
public class FinalProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalProject() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		String userkeyword = request.getParameter("input");
		String kname = new String(userkeyword.getBytes("ISO-8859-1"), "UTF-8");
		
		ArrayList<String> webname = new ArrayList<String>();
		ArrayList<String> weburl = new ArrayList<String>();		
		//ArrayList<Keyword> Keywords = new ArrayList<Keyword>();	
		//Keywords.add(new Keyword(userkeyword, 1.0));

			Execute exe = new Execute();
			exe.Do(kname);
			for (int j = 0; j < exe.arrtree.size(); j++) {
				WebTree treeb = exe.arrtree.get(j);
			    webname.add(treeb.Root.webPage.name)  ;
			    weburl.add(treeb.Root.webPage.url);
			}	
			int ranking = 0;
			PrintWriter out = response.getWriter();		
			//----------------html------------------
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>搜尋結果</title>");
			out.println("<style>");
			out.println("p{");
			out.println("color: #322f11;");
			out.println("font-size: 20px;");
			out.println("font-family: Microsoft JhengHei");
			out.println("}");
			out.println("a{");
			out.println("color:  #322f11;");
			out.println("font-size: 20px;");
			out.println("font-family: Microsoft JhengHei");
			out.println("}");
			out.println("body{");
			out.println("background-image:url("+'"'+"https://scontent-tpe1-1.xx.fbcdn.net/hphotos-xpa1/v/t35.0-12/12494065_1107725385918308_31118122_o.jpg?oh=af7b64b8bb074daffb07b029eefc12e7&oe=56987799"+'"'+");");
			out.println("background-size: cover;");
			out.println("background-repeat:no-repeat;");
			out.println("background-attachment:scroll;");
			out.println("</style>");
			
			
			out.println("</head>");
			out.println("<body>");
			out.println("<p >搜尋結果</p>");
			for (int i = 0; i < webname.size(); i++) {
					String name = webname.get(i);
					String url = weburl.get(i);
					ranking++;
					out.println(ranking+". "+"<a href="+'"'+ url +'"'+">"+ name +"</a>");
					out.println("<br></br>");
			}
			out.println("</body>");
			out.println("</html>");
			//---------------------------------------
			
			out.flush();
			out.close();
		}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
