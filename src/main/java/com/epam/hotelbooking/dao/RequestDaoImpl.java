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
    private static final String CREATE_NEW_ROOM_REQUEST = "insert into reservation (room_capacity,room_class,start_date,end_date,user_id) values (?,?,?,?,?)";
    private static final String FILTER_FOR_ADMIN = "is_approved";
    private static final String FILTER_FOR_CLIENT = "user_id";
    private static final String QUESTION_MARK = "?";
    private static final String INSERT_ROOM_INTO_REQUEST = "update reservation set room_id = ?, is_approved = '1' where id = ?";

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
    public void update(Long itemId, String query, Object... params) throws DaoException {
        executeQueryWithoutReturnValue(query, params, itemId);
    }

    @Override
    public void delete(Long itemId) {
        throw new UnsupportedOperationException(NO_IMPLEMENTATION);
    }

    @Override
    public List<Request> getUnapprovedRequestsForAdmin(int pageNumber) throws DaoException {
        int startElement = (pageNumber - 1) * RECORDS_PER_PAGE;
        return super.getItemsForSinglePage(startElement, EntityType.REQUEST, FILTER_FOR_ADMIN, ZERO);
    }

    @Override
    public List<Request> getRequestsForClient(int pageNumber) throws DaoException {
        int startElement = (pageNumber - 1) * RECORDS_PER_PAGE;
        return super.getItemsForSinglePage(startElement, EntityType.REQUEST, FILTER_FOR_CLIENT, QUESTION_MARK);
    }

    @Override
    public Integer getAmountOfPages() throws DaoException {
        return super.getAmountOfPages(EntityType.REQUEST);
    }

    @Override
    public void insertRoomIntoRequest(Long requestId, Long roomId) throws DaoException {
        update(requestId, INSERT_ROOM_INTO_REQUEST, roomId);
    }
}