package online.c1eye.thanato.controller.v1;


import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.core.annotation.GroupRequired;
import io.github.talelin.core.annotation.PermissionMeta;
import online.c1eye.thanato.dto.book.CreateOrUpdateBookDTO;
import online.c1eye.thanato.model.BookDO;
import online.c1eye.thanato.service.BookService;
import online.c1eye.thanato.vo.CreatedVO;
import online.c1eye.thanato.vo.DeletedVO;
import online.c1eye.thanato.vo.UpdatedVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/book")
@Validated
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/{id}")
    public BookDO getBook(@PathVariable(value = "id") @Positive(message = "{id.positive}") Integer id) {
        BookDO book = bookService.getById(id);
        if (book == null) {
            throw new NotFoundException("book not found", 10022);
        }
        return book;
    }

    @GetMapping("")
    public List<BookDO> getBooks() {
        List<BookDO> books = bookService.findAll();
        return books;
    }


    @GetMapping("/search")
    public List<BookDO> searchBook(@RequestParam(value = "q", required = false, defaultValue = "") String q) {
        List<BookDO> books = bookService.getBookByKeyword("%" + q + "%");
        return books;
    }


    @PostMapping("")
    public CreatedVO createBook(@RequestBody @Validated CreateOrUpdateBookDTO validator) {
        bookService.createBook(validator);
        return new CreatedVO(12);
    }


    @PutMapping("/{id}")
    public UpdatedVO updateBook(@PathVariable("id") @Positive(message = "{id.positive}") Integer id, @RequestBody @Validated CreateOrUpdateBookDTO validator) {
        BookDO book = bookService.getById(id);
        if (book == null) {
            throw new NotFoundException("book not found", 10022);
        }
        bookService.updateBook(book, validator);
        return new UpdatedVO(13);
    }


    @DeleteMapping("/{id}")
    @GroupRequired
    @PermissionMeta(value = "删除图书", module = "图书")
    public DeletedVO deleteBook(@PathVariable("id") @Positive(message = "{id.positive}") Integer id) {
        BookDO book = bookService.getById(id);
        if (book == null) {
            throw new NotFoundException("book not found", 10022);
        }
        bookService.deleteById(book.getId());
        return new DeletedVO(14);
    }


}
