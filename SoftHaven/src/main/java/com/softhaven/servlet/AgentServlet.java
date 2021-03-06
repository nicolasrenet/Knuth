package com.softhaven.servlet;

import com.softhaven.bean.Arrival;
import com.softhaven.dao.ArrivalDao;
import com.softhaven.dao.BerthDAO;
import com.softhaven.dao.UserDAO;
import com.softhaven.dao.VesselDAO;
import com.softhaven.bean.Berth;
import com.softhaven.bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/agent")
public class AgentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AgentServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null){
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            User user = (User) session.getAttribute("user");
            UserDAO userDao = new UserDAO();
            User revised_user = null;
            ArrivalDao arrivalDao = new ArrivalDao();
            try {
                revised_user = userDao.checkPosition(user.getEmail());
            } catch (Exception e) {
                e.printStackTrace();
            }
            assert revised_user != null;
            user.setPosition(revised_user.getPosition());
            session.setAttribute("user", user);
            if (!user.getPosition().equals("agent")) {
                response.sendRedirect(request.getContextPath() + "/" + user.getPosition());
            } else {
                VesselDAO vesselDAO = new VesselDAO();
                BerthDAO berthDAO = new BerthDAO();
                try {
                    ResultSet resultSet = berthDAO.getAllBerthingData();
                    Berth berth;
                    List<Berth> allBerths = new ArrayList<>();
                    while (resultSet.next()) {
                        berth = new Berth();
                        berth.setPort(resultSet.getString("ports.name"));
                        berth.setQuay(resultSet.getString("quays.name"));
                        berth.setType(resultSet.getString("type"));
                        berth.setNumber(resultSet.getString("number"));
                        berth.setShip_imo(resultSet.getInt("ship_imo"));
                        allBerths.add(berth);
                    }
                    request.setAttribute("allBerths", allBerths);
                    List<Arrival> arrivalList = arrivalDao.fetchAllApprovedForms(user.getEmail());
                    if (arrivalList.size() > 0) {
                        request.setAttribute("forms", arrivalList);
                    } else {
                        request.setAttribute("forms", null);
                    }
                    request.getRequestDispatcher("agent.jsp").forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
