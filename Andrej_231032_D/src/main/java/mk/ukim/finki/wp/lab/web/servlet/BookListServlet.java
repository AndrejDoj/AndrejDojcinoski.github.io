//package mk.ukim.finki.wp.lab.web.servlet;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.ukim.finki.wp.lab.model.Book;
//import mk.ukim.finki.wp.lab.service.BookService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet(name = "BookListServlet", urlPatterns = "/servlet")
//public class BookListServlet extends HttpServlet {
//    private final SpringTemplateEngine templateEngine;
//    private final BookService bookService;
//
//    public BookListServlet(SpringTemplateEngine templateEngine, BookService bookService) {
//        this.templateEngine = templateEngine;
//        this.bookService = bookService;
//    }
//
//    @Override
//    @SuppressWarnings("unchecked")
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(request, response);
//
//        List<Book> books = null;
//        List<String> lastViewed = (List<String>) request.getSession().getAttribute("lastViewed");
//
//        String filterName = request.getParameter("filterName");
//        double filterRating = -1;
//
//        try {
//            filterRating = Double.parseDouble(request.getParameter("filterRating"));
//        } catch(Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        if(filterName != null && !filterName.isEmpty() && filterRating != -1) {
//            books = bookService.searchBooks(filterName, filterRating);
//        } else {
//            books = bookService.listAll();
//        }
//
//        WebContext context = new WebContext(webExchange);
//        context.setVariable("books", books);
//        context.setVariable("lastViewedBooks", lastViewed);
//
//        templateEngine.process("listBooks", context, response.getWriter());
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String filterName = request.getParameter("filterName");
//        String filterRating = request.getParameter("filterRating");
//        String params = String.format("filterName=%s&filterRating=%s", filterName, filterRating);
//        response.sendRedirect("/?" + params);
//    }
//}
