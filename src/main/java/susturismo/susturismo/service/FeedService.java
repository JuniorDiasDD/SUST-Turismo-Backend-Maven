package susturismo.susturismo.service;

import org.springframework.data.domain.Sort;
import susturismo.susturismo.domain.Account;
import susturismo.susturismo.domain.Feed;
import susturismo.susturismo.exeption.exeptions.HttpElementNotFoundExeption;
import susturismo.susturismo.exeption.exeptions.HttpUpdateFailedException;
import susturismo.susturismo.repository.AccountRepository;
import susturismo.susturismo.repository.FeedRepository;
import susturismo.susturismo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FeedService {

    @Autowired
    FeedRepository feedRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    LikeFeedService likeFeedService;
    @Autowired
    CommentFeedService commentFeedService;

    public List<Feed> findAll(){
        List<Feed> list=feedRepository.findAll(Sort.by(Sort.Direction.DESC, "criadoEm"));
        String userName=SecurityContextHolder.getContext().getAuthentication().getName();
        UUID id= userRepository.findByUsername(userName).get().getId();
        list.forEach(v->{
            Optional<Account>optional=accountRepository.findAccountByAuth(v.getCriadoPor());
            optional.ifPresent(v::setAccount);

            v.setCount_likes(likeFeedService.findAllByFeedId(v.getId()).size());
            v.setUser_like(!likeFeedService.findByUserId(id,v.getId()));
            v.setComments(commentFeedService.findAllByFeedId(v.getId()));
        });

        return list;
    }
    public List<Feed> findAllStatus(String status){
        List<Feed> list= feedRepository.findAllFeedStatus(status);
        String userName=SecurityContextHolder.getContext().getAuthentication().getName();
        UUID id= userRepository.findByUsername(userName).get().getId();
        list.forEach(v->{
            Optional<Account>optional=accountRepository.findAccountByAuth(v.getCriadoPor());
            optional.ifPresent(v::setAccount);
            v.setCount_likes(likeFeedService.findAllByFeedId(v.getId()).size());
            v.setUser_like(!likeFeedService.findByUserId(id,v.getId()));
            v.setComments(commentFeedService.findAllByFeedId(v.getId()));
        });

        return list;
    }
    public Feed insert(Feed feed){
        String userName=SecurityContextHolder.getContext().getAuthentication().getName();
        UUID id= userRepository.findByUsername(userName).get().getId();
        feed.setCriadoPor(id);
        feed.setAlteradoPor(id);
        feed.setStatus("Active");
        return feedRepository.save(feed);
    }

    public Feed update(Feed feed){
        Optional<Feed> optionalFeed= feedRepository.findById(feed.getId());
        if(optionalFeed.isEmpty()){
            throw new HttpUpdateFailedException("Not exist this Feed");
        }

        if(feed.getDescription()!=null){
            optionalFeed.get().setDescription(feed.getDescription());
        }
        if(feed.getImage()!=null){
            optionalFeed.get().setImage(feed.getImage());
        }
        String userName=SecurityContextHolder.getContext().getAuthentication().getName();
        UUID id= userRepository.findByUsername(userName).get().getId();
        optionalFeed.get().setAlteradoPor(id);

        return feedRepository.save(optionalFeed.get());
    }

    public boolean updateStatus(UUID uuid,String status){

        Optional<Feed> feed = feedRepository.findById(uuid);

        if(feed.isPresent()){
            feed.get().setStatus(status);
            feedRepository.save(feed.get());

            return !feedRepository.findById(uuid).get().getStatus().equals(status);
        }else{
            throw new HttpElementNotFoundExeption("Not exist this Feed");
        }


    }

    public Optional<Feed> findById(UUID id){
        Optional<Feed>feed= feedRepository.findById(id);
        Optional<Account>optional=accountRepository.findAccountByAuth(feed.get().getCriadoPor());
        optional.ifPresent(feed.get()::setAccount);
        String userName=SecurityContextHolder.getContext().getAuthentication().getName();
        UUID user_id= userRepository.findByUsername(userName).get().getId();
        feed.get().setCount_likes(likeFeedService.findAllByFeedId(feed.get().getId()).size());
        feed.get().setUser_like(!likeFeedService.findByUserId(id,feed.get().getId()));
        feed.get().setComments(commentFeedService.findAllByFeedId(feed.get().getId()));
        return feed;
    }
    public boolean delete(UUID id){
        Optional<Feed> feed=feedRepository.findById(id);
        if(feed.isPresent()){
            feedRepository.delete(feed.get());
            return true;
        }
        return false;
    }
}
