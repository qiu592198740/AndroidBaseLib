package com.owl.baselib.utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.Spannable;
import android.text.util.Linkify.MatchFilter;
import android.text.util.Linkify.TransformFilter;
import android.util.Patterns;

public class MyLink {

	public static ArrayList<LinkSpec> link(Spannable spannble){
		ArrayList<LinkSpec> links = new ArrayList<LinkSpec>();
		
		Spannable text = spannble;
		
        gatherLinks(links, text, Patterns.WEB_URL,
                new String[] { "http://", "https://", "rtsp://" },
                sUrlMatchFilter, null);
		return links;
	}
	
    private static final void gatherLinks(ArrayList<LinkSpec> links,
            Spannable s, Pattern pattern, String[] schemes,
            MatchFilter matchFilter, TransformFilter transformFilter) {
        Matcher m = pattern.matcher(s);

        while (m.find()) {
            int start = m.start();
            int end = m.end();

            if (matchFilter == null || matchFilter.acceptMatch(s, start, end)) {
                LinkSpec spec = new LinkSpec();
                String url = makeUrl(m.group(0), schemes, m, transformFilter);

                spec.url = url;
                spec.start = start;
                spec.end = end;

                links.add(spec);
            }
        }
    }
    
    public static class LinkSpec {
       public String url;
       public int start;
       public int end;
    }
    
    private static final String makeUrl(String url, String[] prefixes,
            Matcher m, TransformFilter filter) {
        if (filter != null) {
            url = filter.transformUrl(m, url);
        }

        boolean hasPrefix = false;
        
        for (int i = 0; i < prefixes.length; i++) {
            if (url.regionMatches(true, 0, prefixes[i], 0,
                                  prefixes[i].length())) {
                hasPrefix = true;

                // Fix capitalization if necessary
                if (!url.regionMatches(false, 0, prefixes[i], 0,
                                       prefixes[i].length())) {
                    url = prefixes[i] + url.substring(prefixes[i].length());
                }

                break;
            }
        }

        if (!hasPrefix) {
            url = prefixes[0] + url;
        }

        return url;
    }
	
    public static final MatchFilter sUrlMatchFilter = new MatchFilter() {
        public final boolean acceptMatch(CharSequence s, int start, int end) {
            if (start == 0) {
                return true;
            }

            if (s.charAt(start - 1) == '@') {
                return false;
            }

            return true;
        }
    };
	
}
