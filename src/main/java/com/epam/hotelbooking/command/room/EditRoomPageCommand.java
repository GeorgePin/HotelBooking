package com.epam.hotelbooking.command.room;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hotelbooking.command.util.Command;
import com.epam.hotelbooking.command.util.CommandResult;
import com.epam.hotelbooking.entity.Room;
import com.epam.hotelbooking.entity.RoomPrice;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.exception.ServiceException;
import com.epam.hotelbooking.service.RoomPriceServiceImpl;
import com.epam.hotelbooking.service.RoomServiceImpl;

public class EditRoomPageCommand implements Command {
    private final RoomPriceServiceImpl roomPriceService;
    private final RoomServiceImpl roomService;

    public EditRoomPageCommand(RoomPriceServiceImpl roomPriceService, RoomServiceImpl roomService) {
        this.roomPriceService = roomPriceService;
        this.roomService = roomService;

    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp)
            throws DaoException, ServiceException {
        Long roomId = Long.parseLong(req.getParameter("roomId"));
        List<RoomPrice> listOfPrices = roomPriceService.getRoomPrices();
        Optional<Room> room = roomService.readRoom(roomId);
        setAttributesOnRequest(req, room, roomId, listOfPrices);
        return CommandResult.forward("/WEB-INF/view/admin-pages/editRoom.jsp");
    }

    private void setAttributesOnRequest(HttpServletRequest req, Optional<Room> room, Long roomId,
            List<RoomPrice> listOfPrices) {
        req.setAttribute("room", room.isPresent() ? room.get() : room.isEmpty());
        req.setAttribute("roomId", roomId);
        req.setAttribute("listOfPrices", listOfPrices);
    }
}