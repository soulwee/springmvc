package com.seecen.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.seecen.dao.DepartmentDao;
import com.seecen.dao.EmployeeDao;
import com.seecen.entity.Employee;
import com.seecen.util.ValidList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    @ModelAttribute
    public void getEmployee(@RequestParam(value="id",required=false) Integer id,
                            Map<String, Object> map){
        if(id != null){
            map.put("employee", employeeDao.get(id));
        }
    }

    @RequestMapping(value="/emp", method=RequestMethod.PUT)
    public String update(Employee employee){
        employeeDao.save(employee);

        return "redirect:/emps";
    }

    @RequestMapping(value="/emp/{id}", method=RequestMethod.GET)
    public String input(@PathVariable("id") Integer id, Map<String, Object> map){
        map.put("employee", employeeDao.get(id));
        map.put("departments", departmentDao.getDepartments());
        return "input";
    }

    @RequestMapping(value="/emp/{id}", method=RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }

    @RequestMapping(value="/emp", method=RequestMethod.POST)
    public String save(@Valid Employee employee, Errors result,
                       Map<String, Object> map){
        System.out.println("save: " + employee);
        if(result.getErrorCount() > 0){
            System.out.println("出错了!");

            for(FieldError error:result.getFieldErrors()){
                System.out.println(error.getField() + ":" + error.getDefaultMessage());
            }

            //若验证出错, 则转向定制的页面
            map.put("departments", departmentDao.getDepartments());
            return "input";
        }
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @RequestMapping(value="/emp", method=RequestMethod.GET)
    public String input(Map<String, Object> map){
        map.put("departments", departmentDao.getDepartments());
        map.put("employee", new Employee());
        return "input";
    }

    @RequestMapping("/emps")
    public String list(Map<String, Object> map){
        map.put("employees", employeeDao.getAll());
        return "list";
    }

    //数据校验 可以重定向校验
    @RequestMapping(value="/autoemp", method=RequestMethod.GET)
    public String autoemp(RedirectAttributes attributes){
        List<Employee> list = new ArrayList<>();
        Employee e = new Employee();
        e.setEmail("w4");
        list.add(e);
        e = new Employee();
        e.setEmail("w2344");
        list.add(e);
        ValidList<Employee> employeeList = new ValidList<>(list);
        attributes.addFlashAttribute("employeeList", employeeList);

        return "redirect:validate2";
    }

    /**
     * 需校验的 Bean 对象和其绑定结果对象或错误对象时成对出现的，它们
     * 之间不允许声明其他的入参
     */
    //BindingResult 或 Errors  BindingResult 扩展了 Errors 接口
    @RequestMapping(value="/validate", method=RequestMethod.GET)
    public String list(@ModelAttribute @Valid ArrayList<Employee> validList,Errors result,Model model) {
        if(result.getErrorCount() > 0){
            System.out.println("出错了!");

            for(FieldError error:result.getFieldErrors()){
                System.out.println(error.getField() + ":" + error.getDefaultMessage());
            }

            //若验证出错, 则转向定制的页面
//            map.put("departments", departmentDao.getDepartments());
            return "input";
        }
//        employeeDao.save(employee);
        return "redirect:/emps";
    }
    @RequestMapping(value="/validate2", method=RequestMethod.GET)
    public String doSth(@ModelAttribute @Valid ValidList<Employee> employeeList, BindingResult result, Model model){
        model.addAttribute("error",result);
        if(result.getErrorCount() > 0){
            System.out.println("出错了!");

            for(FieldError error:result.getFieldErrors()){
                System.out.println(error.getField() + ":" + error.getDefaultMessage());
            }
            //若验证出错, 则转向定制的页面
//            map.put("departments", departmentDao.getDepartments());
            return "empList";
        }
        return "redirect:/emps";
    }
//	@InitBinder
//	public void initBinder(WebDataBinder binder){
//		binder.setDisallowedFields("lastName");
//	}

}
