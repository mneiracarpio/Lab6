package ca.sait.lab6.servlets;

import ca.sait.lab6.models.Role;
import ca.sait.lab6.models.User;
import ca.sait.lab6.services.RoleService;
import ca.sait.lab6.services.UserService;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Marco
 */
public class UserServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService service = new UserService();
        RoleService rolService = new RoleService();
        
        String action = request.getParameter("action");
        String email = request.getParameter("email");
        request.setAttribute("showDiv", "none");
        if (action != null && action.equals("delete")) {
            try {
                boolean delete = service.delete(email);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if (action != null && action.equals("edit")){
            try {
                User user = service.get(email);
                List<Role> roles = rolService.getAll();
//                Role role = user.
                request.setAttribute("user", user);
                request.setAttribute("roles", roles);
                request.setAttribute("showDiv", "edit");
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action != null && action.equals("add")){
            try {
                List<Role> roles = rolService.getAll();
                request.setAttribute("roles", roles);
                request.setAttribute("showDiv", "add");
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                
        try {
            List<User> users = service.getAll();
            request.setAttribute("users", users);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService service = new UserService();
        RoleService rolService = new RoleService();
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        int roleId = Integer.parseInt(request.getParameter("roleId"));
        
        String action = request.getParameter("action");
        try {
            if (action != null && action.equals("add")) {
                Role role = rolService.get(roleId);
                boolean inserted = service.insert(email, true, firstName, lastName, password, role);
                request.setAttribute("showDiv", "add");
            } else {
                Role role = rolService.get(roleId);
                boolean updated = service.update(email, true, firstName, lastName, password, role);
                request.setAttribute("showDiv", "edit");
            }
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        //this.getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        
        response.sendRedirect("user");
    }

}
