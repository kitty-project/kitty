package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.PathParam;
import com.julianjupiter.kitty.util.Constants;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Julian Jupiter
 */
class PathParamsFactory {
    private final URI target;

    private PathParamsFactory(URI target) {
        this.target = target;
    }

    public static PathParamsFactory create(URI target) {
        return new PathParamsFactory(target);
    }

    public PathParam[] createPathParams(Route route) {
        var routePath = route.path();
        if (routePath != null) {
            var pathSegments = this.target.getPath().split(Constants.Characters.FORWARD_SLASH);
            var definedPathSegments = routePath.split(Constants.Characters.FORWARD_SLASH);
            if (pathSegments.length == definedPathSegments.length) {
                return this.paramsKeyValue(definedPathSegments)
                        .entrySet().stream()
                        .map(entry -> {
                            var pathName = pathSegments[entry.getKey()];
                            return new KittyPathParam(entry.getValue(), pathName);
                        })
                        .toList()
                        .toArray(new KittyPathParam[0]);
            }
        }

        return new PathParam[0];
    }

    private Map<Integer, String> paramsKeyValue(String[] definedPathSegments) {
        var map = new HashMap<Integer, String>();
        for (var i = 0; i < definedPathSegments.length; i++) {
            var segment = definedPathSegments[i];
            if (segment.startsWith(Constants.Characters.CURLY_BRACE_LEFT) && segment.endsWith(Constants.Characters.CURLY_BRACE_RIGHT)) {
                var name = segment.substring(1, segment.indexOf(Constants.Characters.CURLY_BRACE_RIGHT));
                if (name.contains(Constants.Characters.COLON)) {
                    name = name.substring(0, name.indexOf(Constants.Characters.COLON));
                }

                map.put(i, name);
            }
        }

        return map;
    }
}
