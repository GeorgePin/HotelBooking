package com.epam.hotelbooking.dao;

import java.sql.Date;
import java.sql.SQLException;
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
    private static final String NOT_APPROVED_FILTER_VALUE = "0";
    private static final String INSERT_ROOM_INTO_REQUEST = "update reservation set room_id = ?, is_approved = '1' where id = ?";

    public RequestDaoImpl(ProxyConnection proxyConnection, RowMapper<Request> rowMapper) {
        super(proxyConnection, rowMapper);
    }

    @Override
    public boolean create(Request item) throws DaoException {
        int roomCapacity = item.getRoomCapacity();
        String roomClass = item.getRoomClass()
                .toString();
        Date startDate = item.getStartDate();
        Date endDate = item.getEndDate();
        Long userId = item.getUserId();
        try {
            return executeQueryWithoutReturnValue(CREATE_NEW_ROOM_REQUEST, roomCapacity, roomClass, startDate, endDate,
                    userId);
        } catch (SQLException exception) {
            throw new DaoException("Exception during creating item", exception);
        }
    }

    @Override
    public Optional<Request> read(Long itemId) throws DaoException {
        Optional<Request> request;
        try {
            request = executeForSingleResult(FIND_REQUEST_BY_ID, itemId);
        } catch (SQLException exception) {
            throw new DaoException("Exception during reading item", exception);
        }
        return request;
    }

    @Override
    public boolean update(Long itemId, Object... params) throws DaoException {
        Long roomId = (Long) params[0];
        boolean hasUpdated;
        try {
            hasUpdated = executeQueryWithoutReturnValue(INSERT_ROOM_INTO_REQUEST, roomId, itemId);
        } catch (SQLException exception) {
            throw new DaoException("Exception during updating item", exception);
        }
        return hasUpdated;
    }

    @Override
    public boolean delete(Long itemId) {
        throw new UnsupportedOperationException("No implementation");
    }

    @Override
    public List<Request> getRequestsForAdmin(int startElement) throws DaoException {
        List<Request> requests;
        try {
            requests = getItemsForPage(startElement, EntityType.REQUEST, FILTER_FOR_ADMIN, NOT_APPROVED_FILTER_VALUE);
        } catch (SQLException exception) {
            throw new DaoException("Exception during reading items", exception);
        }
        return requests;
    }

    @Override
    public List<Request> getRequestsForClient(int startElement) throws DaoException {
        List<Request> requests;
        try {
            requests = getItemsForPage(startElement, EntityType.REQUEST, FILTER_FOR_CLIENT, QUESTION_MARK);
        } catch (SQLException exception) {
            throw new DaoException("Exception during reading items", exception);
        }
        return requests;
    }
}