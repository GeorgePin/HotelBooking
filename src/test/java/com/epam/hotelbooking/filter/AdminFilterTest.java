package com.epam.hotelbooking.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mockito;

public class AdminFilterTest {

    @Test
    public void testDoFilterShouldResumeChainingWhenAllDataContainsInResultSetAndValid()
            throws ServletException, IOException {

        // given
        AdminFilter adminFilter = new AdminFilter();
        HttpServletRequest mockReq = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse mockResp = Mockito.mock(HttpServletResponse.class);
        FilterChain mockFilterChain = Mockito.mock(FilterChain.class);
        FilterConfig mockFilterConfig = Mockito.mock(FilterConfig.class);
        // when
        
        // then
        adminFilter.init(mockFilterConfig);
        adminFilter.doFilter(mockReq, mockResp, mockFilterChain);
        adminFilter.destroy();
    }
}
