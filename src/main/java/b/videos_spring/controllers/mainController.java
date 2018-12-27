package b.videos_spring.controllers;

import b.videos_spring.DAO.contactDAO;
import b.videos_spring.models.contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class mainController {

    @Autowired
    private contactDAO contactDAO;

    @GetMapping("/")
    public String home(Model model) {
        String msg = "/ is work";
        System.out.println(msg);
        model.addAttribute("msg", msg);
        return "index";
    }


    @PostMapping("/save")
    public String send(
            contact contact,
            @RequestParam MultipartFile avatar) throws IOException {
        //робимо запит параметру через MultipartFile, стаєм назву файлу через метод .getOriginalFilename()

        String path =
                System.getProperty("user.home")
                        + File.separator
                        + "images"
                        + File.separator
                        + avatar.getOriginalFilename();

        avatar.transferTo(new File(path));
        System.out.println(contact);
        contact.setAvatar(avatar.getOriginalFilename());
        contactDAO.save(contact);
        return "redirect:/";
    }


    @GetMapping("/contactdetail/{id}")
    public String showCon(Model model, @PathVariable int id) {
        contact contact = contactDAO.getOne(id);
        model.addAttribute("contact", contact);

        return "singleContact";
    }

    @PostMapping("/updatecontact")
    public String updatecontact(contact contact) {
        contactDAO.save(contact);//update старого контакту
        return "redirect:/";
    }


    @GetMapping("/allcontacts")
    public String allcontacts(Model model) {
        List<contact> list = contactDAO.findAll(Sort.by("name"));
        model.addAttribute("list", list);
        return "contactList";
    }

}
