package com.li.experience.common.config;


import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: experience
 * @description:
 * @author: Liwei
 * @create: 2019-07-29 11:59
 **/

public class CustomHttpTrace implements HttpTraceRepository {
    private int capacity = 1000;
    private boolean reverse = true;
    private final List<HttpTrace> traces = new LinkedList();

    public CustomHttpTrace() {
    }

    public void setReverse(boolean reverse) {
        List var2 = this.traces;
        synchronized (this.traces) {
            this.reverse = reverse;
        }
    }

    public void setCapacity(int capacity) {
        List var2 = this.traces;
        synchronized (this.traces) {
            this.capacity = capacity;
        }
    }

    public List<HttpTrace> findAll() {
        List var1 = this.traces;
        synchronized (this.traces) {
            return Collections.unmodifiableList(new ArrayList(this.traces));
        }
    }

    public void add(HttpTrace trace) {
        List var2 = this.traces;
        synchronized (this.traces) {
            while (this.traces.size() >= this.capacity) {
                this.traces.remove(this.reverse ? this.capacity - 1 : 0);
            }
            String url = trace.getRequest().getUri().getRawPath();
            if (!url.contains(".") && !url.contains(";") && !traces.contains(trace)){
                if (this.reverse) {
                    this.traces.add(0, trace);
                } else {
                    this.traces.add(trace);
                }
            }
        }
    }
}

