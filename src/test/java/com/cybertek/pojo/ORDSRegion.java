package com.cybertek.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
public class ORDSRegion {

    private int region_id;
    private String region_name;
    private List<Link> links;






}
