package org.apache.jsp.WEB_002dINF.jsp.home;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.util.*;

public final class header_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(1);
    _jspx_dependants.add("/WEB-INF/jsp/public/header.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fa_005ftarget_005fnamespace_005faction;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fs_005fa_005ftarget_005fnamespace_005faction = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fs_005fa_005ftarget_005fnamespace_005faction.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
      out.write("<html>\r\n");
      out.write("  <head>\r\n");
      out.write("    <base href=\"");
      out.print(basePath);
      out.write("\">\r\n");
      out.write("    \r\n");
      out.write("    <title>header</title>\r\n");
      out.write("    <link type=\"text/css\" rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/style/blue/top.css\" />\r\n");
      out.write("     ");
      out.write("\r\n");
      out.write("<script language=\"javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/script/jquery.js\"></script>\r\n");
      out.write("<script language=\"javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/script/pageCommon.js\" charset=\"utf-8\"></script>\r\n");
      out.write("<script language=\"javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/script/PageUtils.js\" charset=\"utf-8\"></script>\r\n");
      out.write("<link type=\"text/css\" rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/style/blue/pageCommon.css\" />\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("  </head>\r\n");
      out.write("  \r\n");
      out.write(" <body class=\"PageBody\" style=\"margin: 0\">\r\n");
      out.write(" \r\n");
      out.write("\t<div id=\"Head1\">\r\n");
      out.write("\t\t<div id=\"Logo\">\r\n");
      out.write("\t\t\t<a id=\"msgLink\" href=\"javascript:void(0)\"></a>\r\n");
      out.write("            <font color=\"#0000CC\" style=\"color:#F1F9FE; font-size:28px; font-family:Arial Black, Arial\">CBLUE OA</font> \r\n");
      out.write("\t\t\t<!--<img border=\"0\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/style/blue/images/logo.png\" />-->\r\n");
      out.write("        </div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<div id=\"Head1Right\">\r\n");
      out.write("\t\t\t<div id=\"Head1Right_UserName\">\r\n");
      out.write("                <img border=\"0\" width=\"13\" height=\"14\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/style/images/top/user.gif\" /> 您好，<b>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${loginUser.name}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</b>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div id=\"Head1Right_UserDept\"></div>\r\n");
      out.write("\t\t\t<div id=\"Head1Right_UserSetup\">\r\n");
      out.write("            \t<a href=\"javascript:void(0)\">\r\n");
      out.write("\t\t\t\t\t<img border=\"0\" width=\"13\" height=\"14\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/style/images/top/user_setup.gif\" /> 个人设置\r\n");
      out.write("\t\t\t\t</a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div id=\"Head1Right_Time\"></div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("        <div id=\"Head1Right_SystemButton\">\r\n");
      out.write("            ");
      if (_jspx_meth_s_005fa_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("\t\t\r\n");
      out.write("        <div id=\"Head1Right_Button\">\r\n");
      out.write("            <a target=\"desktop\" href=\"/desktop?method=show\">\r\n");
      out.write("\t\t\t\t<img width=\"65\" height=\"20\" alt=\"显示桌面\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/style/blue/images/top/desktop.gif\" />\r\n");
      out.write("\t\t\t</a>\r\n");
      out.write("        </div>\r\n");
      out.write("\t</div>\r\n");
      out.write("    \r\n");
      out.write("    <div id=\"Head2\">\r\n");
      out.write("        <div id=\"Head2_Awoke\">\r\n");
      out.write("            <ul id=\"AwokeNum\">\r\n");
      out.write("                <li><a target=\"desktop\" href=\"javascript:void(0)\">\r\n");
      out.write("\t\t\t\t\t\t<img border=\"0\" width=\"11\" height=\"13\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/style/images/top/msg.gif\" /> 消息\r\n");
      out.write("\t\t\t\t\t\t<span id=\"msg\"></span>\r\n");
      out.write("\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t</li>\r\n");
      out.write("                <li class=\"Line\"></li>\r\n");
      out.write("                <li><a target=\"desktop\" href=\"javascript:void(0)\">\r\n");
      out.write("\t\t\t\t\t\t<img border=\"0\" width=\"16\" height=\"11\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/style/images/top/mail.gif\" /> 邮件\r\n");
      out.write("\t\t\t\t\t\t<span id=\"mail\"></span>\r\n");
      out.write("\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t</li>\r\n");
      out.write("                <li class=\"Line\"></li>\r\n");
      out.write("\t\t\t\t  <!-- 是否有待审批文档的提示1，数量 -->\r\n");
      out.write("                <li><a href=\"Flow_Formflow/myTaskList.html\" target=\"desktop\">\r\n");
      out.write("                \t\t<img border=\"0\" width=\"12\" height=\"14\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/style/images/top/wait.gif\" /> \r\n");
      out.write("                \t\t待办事项（<span id=\"wait\" class=\"taskListSize\">1</span>）\r\n");
      out.write("                \t</a>\r\n");
      out.write("                </li>\r\n");
      out.write("\t\t\t\t  \r\n");
      out.write("                <!-- 是否有待审批文档的提示2，提示审批 -->\r\n");
      out.write("                <li id=\"messageArea\">您有 1 个待审批文档，请及时审批！★★★★★</li>\r\n");
      out.write("            </ul>\r\n");
      out.write("        </div>\r\n");
      out.write("        \r\n");
      out.write("\t\t<div id=\"Head2_FunctionList\">\r\n");
      out.write("\t\t\t<marquee style=\"WIDTH: 100%;\" onMouseOver=\"this.stop()\" onMouseOut=\"this.start()\" \r\n");
      out.write("\t\t\t\tscrollamount=1 scrolldelay=30 direction=left>\r\n");
      out.write("\t\t\t\t<b>滚动的消息</b>\r\n");
      out.write("\t\t\t</marquee>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_s_005fa_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:a
    org.apache.struts2.views.jsp.ui.AnchorTag _jspx_th_s_005fa_005f0 = (org.apache.struts2.views.jsp.ui.AnchorTag) _005fjspx_005ftagPool_005fs_005fa_005ftarget_005fnamespace_005faction.get(org.apache.struts2.views.jsp.ui.AnchorTag.class);
    _jspx_th_s_005fa_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fa_005f0.setParent(null);
    // /WEB-INF/jsp/home/header.jsp(40,12) null
    _jspx_th_s_005fa_005f0.setDynamicAttribute(null, "target", new String("_parent"));
    // /WEB-INF/jsp/home/header.jsp(40,12) name = action type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fa_005f0.setAction("user_logout");
    // /WEB-INF/jsp/home/header.jsp(40,12) name = namespace type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fa_005f0.setNamespace("/");
    int _jspx_eval_s_005fa_005f0 = _jspx_th_s_005fa_005f0.doStartTag();
    if (_jspx_eval_s_005fa_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fa_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fa_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fa_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t<img width=\"78\" height=\"20\" alt=\"退出系统\" src=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("/style/blue/images/top/logout.gif\" />\r\n");
        out.write("\t\t\t");
        int evalDoAfterBody = _jspx_th_s_005fa_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fa_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fa_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fa_005ftarget_005fnamespace_005faction.reuse(_jspx_th_s_005fa_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fa_005ftarget_005fnamespace_005faction.reuse(_jspx_th_s_005fa_005f0);
    return false;
  }
}
