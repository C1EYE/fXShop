package online.c1eye.thanato.service;


import online.c1eye.thanato.dto.book.CreateOrUpdateBookDTO;
import online.c1eye.thanato.model.BookDO;

import java.util.List;

public interface BookService {

    boolean createBook(CreateOrUpdateBookDTO validator);

    List<BookDO> getBookByKeyword(String q);

    boolean updateBook(BookDO book, CreateOrUpdateBookDTO validator);

    BookDO getById(Integer id);

    List<BookDO> findAll();

    boolean deleteById(Integer id);
}
