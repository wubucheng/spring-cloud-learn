package me.wubc.book.controller;

import me.wubc.book.entity.BookPo;
import me.wubc.book.entity.ResponseResult;
import org.springframework.web.bind.annotation.*;


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
