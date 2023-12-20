package susturismo.susturismo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import susturismo.susturismo.domain.*;
import susturismo.susturismo.exeption.exeptions.HttpInsertFailedException;
import susturismo.susturismo.repository.LikeFeedRepository;
import susturismo.susturismo.repository.UserRepository;

import java.util.*;

@Service
public class LikeFeedService {

    @Autowired
    LikeFeedRepository likeFeedRepository;
    @Autowired
    UserRepository userRepository;

    public List<LikeFeed> findAll(){
        return likeFeedRepository.findAll();
    }
    public List<LikeFeed> findAllByFeedId(UUID id){
        return likeFeedRepository.findAllByFeedId(id);
    }
    public boolean findByUserId(UUID user_id, UUID feed_id){
        Optional<LikeFeed> optional=likeFeedRepository.findByUserIdAndFeedId(user_id,feed_id);

        return optional.isEmpty();
    }
    @Transactional
    public LikeFeed insert(UUID feed_id){
        String userName= SecurityContextHolder.getContext().getAuthentication().getName();
        UUID id= userRepository.findByUsername(userName).get().getId();

        boolean check= findByUserId(id,feed_id);
        if(!check){
            throw new HttpInsertFailedException("Já existe um like desse utilizador nesse post");
        }

        LikeFeed likeFeed =new LikeFeed();
        likeFeed.setFeed_id(feed_id);
        likeFeed.setUser_id(id);
        likeFeed.setCriadoPor(id);
        likeFeed.setAlteradoPor(id);

        return likeFeedRepository.save(likeFeed);
    }


    public boolean delete(UUID feed_id){

        String userName= SecurityContextHolder.getContext().getAuthentication().getName();
        UUID id= userRepository.findByUsername(userName).get().getId();
        Optional<LikeFeed> optional=likeFeedRepository.findByUserIdAndFeedId(id,feed_id);

        if(optional.isPresent()){
           likeFeedRepository.delete(optional.get());

        }else{
            throw new HttpInsertFailedException("Já existe um like desse utilizador nesse post");
        }
       return findByUserId(id,feed_id);
    }

}
