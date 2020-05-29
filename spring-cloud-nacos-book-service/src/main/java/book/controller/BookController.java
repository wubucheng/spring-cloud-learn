package book.controller;


import book.entity.BookPo;
import book.entity.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author wbc
 * @date 2020/5/25
 * @desc
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @GetMapping("/getByName")
    public ResponseResult getByName(@RequestParam String name) {
        BookPo bookPo = new BookPo();
        bookPo.setName(name);
        bookPo.setAuth("author");
        return new ResponseResult<>(bookPo);
    }

}
