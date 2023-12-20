package susturismo.susturismo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import susturismo.susturismo.domain.Account;
import susturismo.susturismo.domain.CommentFeed;
import susturismo.susturismo.domain.LikeFeed;
import susturismo.susturismo.exeption.exeptions.HttpInsertFailedException;
import susturismo.susturismo.repository.AccountRepository;
import susturismo.susturismo.repository.CommentFeedRepository;
import susturismo.susturismo.repository.LikeFeedRepository;
import susturismo.susturismo.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommentFeedService {

    @Autowired
    CommentFeedRepository commentFeedRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;


    public List<CommentFeed> findAllByFeedId(UUID id){
        List<CommentFeed>list= commentFeedRepository.findAllByFeedId(id);
        list.forEach(v->{
            Optional<Account>optional=accountRepository.findAccountByAuth(v.getCriadoPor());
            optional.ifPresent(v::setAccount);

        });
        return list;
    }
    public boolean findByUserId(UUID user_id, UUID feed_id){
        Optional<CommentFeed> optional=commentFeedRepository.findByUserIdAndFeedId(user_id,feed_id);
        return optional.isEmpty();
    }
    @Transactional
    public CommentFeed insert(UUID feed_id, String comment){
        String userName= SecurityContextHolder.getContext().getAuthentication().getName();
        UUID id= userRepository.findByUsername(userName).get().getId();

        CommentFeed commentFeed =new CommentFeed();
        commentFeed.setFeed_id(feed_id);
        commentFeed.setUser_id(id);
        commentFeed.setCriadoPor(id);
        commentFeed.setAlteradoPor(id);
        commentFeed.setComment(comment);
        return commentFeedRepository.save(commentFeed);
    }


    public boolean delete(UUID comment_id){
        Optional<CommentFeed> optional=commentFeedRepository.findById(comment_id);

        if(optional.isPresent()){
           commentFeedRepository.delete(optional.get());

        }else{
            throw new HttpInsertFailedException("Dados Invalido");
        }
       return commentFeedRepository.findById(comment_id).isPresent();
    }

}
