package com.jpaShop.book.service;

import com.jpaShop.book.domain.item.Item;
import com.jpaShop.book.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    // 아이템 저장
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    // 아이템 전체 찾기
    public List<Item> findItems(){
        return itemRepository.findAll();
    }
    // 아이템 하나 찾기
    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }

}
