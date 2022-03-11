package com.epam.hotelbooking.dao;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.epam.hotelbooking.connection.ProxyConnection;
import com.epam.hotelbooking.entity.EntityType;
import com.epam.hotelbooking.entity.Request;
import com.epam.hotelbooking.exception.DaoException;
import com.epam.hotelbooking.mapper.RowMapper;

public class RequestDaoImpl extends AbstractDao<Request> implements RequestDao {
    private static final String FIND_REQUEST_BY_ID = "select * from reservation where id=?";
    private static final String CREATE_NEW_ROOM_REQUEST = "insert into reservation (room_capacity,room_class,start_date,end_date,user_id)"
            + " values (?,?,?,?,?)";
    private static final String FILTER_FOR_ADMIN = "is_approved";
    private static final String FILTER_FOR_CLIENT = "user_id";
    private static final String QUESTION_MARK = "user_id";
    private static final String FIND_AMOUNT_OF_REQUESTS_BY_USER_ID = "select * from reservation where user_id=?";
    private static final String INSERT_ROOM_INTO_REQUEST = "update reservation set room_id = ?, is_approved = '1' where id = ?";
    private static final String SELECT_ALL_USER_REQUESTS = "SELECT " + "    reservation.id,"
            + "    reservation.room_id," + "    reservation.user_id," + "    reservation.start_date,"
            + "    reservation.end_date," + "    reservation.room_capacity," + "    reservation.room_class,"
            + "    reservation.is_approved," + "    room_price.price" + " FROM " + " reservation" + " LEFT JOIN"
            + "    room ON room.id = reservation.room_id" + " LEFT JOIN"
            + " room_price ON room_price.id = room.room_price_id" + " WHERE" + "    reservation.user_id=? limit ?, ?";
//TODO those constants should be formated
    private static final String UNAPPROVED_REQUESTS = "select * from reservation where is_approved=0 limit ?, ?";

    public RequestDaoImpl(ProxyConnection proxyConnection, RowMapper<Request> rowMapper) {
        super(proxyConnection, rowMapper);
    }

    @Override
    public void create(Request item) throws DaoException {
        int roomCapacity = item.getRoomCapacity();
        String roomClass = item.getRoomClass()
                .toString();
        Date startDate = item.getStartDate();
        Date endDate = item.getEndDate();
        Long userId = item.getUserId();
        executeQueryWithoutReturnValue(CREATE_NEW_ROOM_REQUEST, roomCapacity, roomClass, startDate, endDate, userId);
    }

    @Override
    public Optional<Request> read(Long itemId) throws DaoException {
        Optional<Request> request = executeForSingleResult(FIND_REQUEST_BY_ID, itemId);
        return request;
    }

    @Override
    public void update(String query, Object... params) throws DaoException {
        executeQueryWithoutReturnValue(query, params);
    }

    @Override
    public void delete(Long itemId) {
        throw new UnsupportedOperationException(NO_IMPLEMENTATION);
    }

    @Override
    public List<Request> getUnapprovedRequestsForAdmin(int pageNumber) throws DaoException {
        int startElement = (pageNumber - 1) * RECORDS_PER_PAGE;
        return super.executeQuery(UNAPPROVED_REQUESTS, startElement, RECORDS_PER_PAGE);
    }

    @Override
    public List<Request> getRequestsForClient(int pageNumber, Long userId) throws DaoException {
        int startElement = (pageNumber - 1) * RECORDS_PER_PAGE;
        return super.executeQuery(SELECT_ALL_USER_REQUESTS, userId, startElement, RECORDS_PER_PAGE);
    }

    @Override
    public Integer getAmountOfPagesForClient(Long userId) throws DaoException {
        return super.getAmountOfPages(EntityType.REQUEST, FILTER_FOR_CLIENT, userId.toString());
    }

    @Override
    public Integer getAmountOfRequestsPagesForAdmin() throws DaoException {
        return super.getAmountOfPages(EntityType.REQUEST, FILTER_FOR_ADMIN, ZERO);
    }

    @Override
    public void insertRoomIntoRequest(Long requestId, Long roomId) throws DaoException {
        update(INSERT_ROOM_INTO_REQUEST, roomId, requestId);
    }
}