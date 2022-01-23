#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.external.service.jsonplaceholder;

import java.util.Map;

public interface IJsonPlaceholderService {
    Map getPost(int id);
}
