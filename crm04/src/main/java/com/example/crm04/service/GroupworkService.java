package com.example.crm04.service;

import com.example.crm04.Entity.GroupworkEntity;
import com.example.crm04.Entity.RolesEntity;
import com.example.crm04.Repository.GroupworkRepository;
import com.example.crm04.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupworkService {

    @Autowired
    private DateUtils dateUtils;

    @Autowired
    private GroupworkRepository groupworkRepository;

    public boolean insertGroupwork(String name, String startDate, String endDate){
        boolean flag = false;

        GroupworkEntity groupworkEntity = new GroupworkEntity();
        if (!name.isEmpty() && dateUtils.isValidDate(startDate) == true &&
                dateUtils.isValidDate(endDate) == true && dateUtils.compareDate(startDate,endDate) == true) {

            String start_date_change = dateUtils.changeDate(startDate);
            String end_date_change = dateUtils.changeDate(endDate);

            groupworkEntity.setName(name);
            groupworkEntity.setStartDate(start_date_change);
            groupworkEntity.setEndDate(end_date_change);
            groupworkRepository.save(groupworkEntity);
            System.out.println(start_date_change + " " + end_date_change);

            flag = true;
        }
        return flag;
    }

    public List<GroupworkEntity> getAllGroupwork(){

        return groupworkRepository.findAll();
    }

    public void deleteById(int id){
        groupworkRepository.deleteById(id);
    }

    public GroupworkEntity getGroupworkById(int id){
        GroupworkEntity dataRole = null;
        Optional<GroupworkEntity> groupworkEntity = groupworkRepository.findById(id);
        if(groupworkEntity.isPresent()){

            dataRole =  groupworkEntity.get();
        }
        System.out.println("Kiểm tra"+ dataRole);
        return dataRole;
    }

    public boolean updateGroupworkById(int id, String name, String startDate, String endDate){
        boolean flag = false;
        GroupworkEntity groupworkEntity = new GroupworkEntity();

        if(!name.isEmpty() && dateUtils.isValidDate(startDate) == true &&
                dateUtils.isValidDate(endDate) == true && dateUtils.compareDate(startDate,endDate) == true){
            String startDateChange = dateUtils.convertDateFormat(startDate);
            String endDateChange = dateUtils.convertDateFormat(endDate);

            groupworkEntity.setId(id);
            groupworkEntity.setName(name);
            groupworkEntity.setStartDate(startDateChange);
            groupworkEntity.setEndDate(endDateChange);

            groupworkRepository.save(groupworkEntity);
            flag = true;
        }

        return flag;
    }
}
