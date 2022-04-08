package com.epam.hotelbooking.command.room;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.command.util.Command;
import com.epam.hotelbooking.command.util.CommandResult;
import com.epam.hotelbooking.entity.ItemsDto;
import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.RoomServiceImpl;

public class RoomsPageCommand implements Command {

    private final RoomServiceImpl roomService;

    public RoomsPageCommand(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        int page = Integer.parseInt(req.getParameter("page"));
        ItemsDto<Room> transferObject = roomService.getRoomsForView(page);
        req.setAttribute("roomsList", transferObject.getItems());
        req.setAttribute("numberOfPages", transferObject.getAmountOfPages());
        return CommandResult.forward("/WEB-INF/view/admin-pages/rooms.jsp");
    }
}
