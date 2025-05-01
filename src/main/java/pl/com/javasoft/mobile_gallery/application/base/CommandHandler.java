package pl.com.javasoft.mobile_gallery.application.base;

public interface CommandHandler<TCommand extends Command<TResult>, TResult> extends BaseHandler<TCommand, TResult> {
    
}
