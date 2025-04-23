package com.example.task51c;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class NewsDetailFragment extends Fragment {
    private ImageView newsImage;
    private TextView newsTitle;
    private TextView newsContent;
    private RecyclerView relatedNewsRecyclerView;
    private RelatedNewsAdapter relatedNewsAdapter;
    private News currentNews;

    public static NewsDetailFragment newInstance(News news) {
        NewsDetailFragment fragment = new NewsDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("news", news);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            currentNews = getArguments().getParcelable("news");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_detail, container, false);
        
        // Initialize views
        newsImage = view.findViewById(R.id.iv_news_image);
        newsTitle = view.findViewById(R.id.tv_news_title);
        newsContent = view.findViewById(R.id.tv_news_content);
        relatedNewsRecyclerView = view.findViewById(R.id.rv_related_news);

        // Setup RecyclerView
        relatedNewsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        relatedNewsAdapter = new RelatedNewsAdapter();
        relatedNewsRecyclerView.setAdapter(relatedNewsAdapter);

        // Set click listener for related news
        relatedNewsAdapter.setOnNewsClickListener(news -> {
            // Replace current fragment with new instance
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, NewsDetailFragment.newInstance(news))
                    .addToBackStack(null)
                    .commit();
        });

        populateNewsDetail();
        loadRelatedNews();

        return view;
    }

    private void populateNewsDetail() {
        if (currentNews != null) {
            // Load image using Glide
            Glide.with(requireContext())
                    .load(currentNews.getImageUrl())
                    .into(newsImage);

            newsTitle.setText(currentNews.getTitle());
            
            // Generate detailed content based on the news title
            String detailedContent = generateDetailedContent(currentNews.getTitle());
            newsContent.setText(detailedContent);
        }
    }

    private String generateDetailedContent(String title) {
        switch (title) {
            case "Global Tech Summit 2024":
                return "The Global Tech Summit 2024, held in Silicon Valley, brought together over 10,000 industry leaders, innovators, and policymakers from around the world. The three-day event focused on the future of artificial intelligence, digital transformation, and sustainable technology solutions.\n\n" +
                       "Key highlights included:\n" +
                       "• Breakthrough discussions on AI ethics and regulation\n" +
                       "• Launch of new international tech standards\n" +
                       "• Announcement of $1 billion investment in green technology\n" +
                       "• Partnership agreements between major tech companies\n\n" +
                       "The summit concluded with a joint declaration to promote responsible AI development and digital inclusion worldwide.";
            
            case "Climate Change Breakthrough":
                return "A team of international scientists has announced a revolutionary method to reduce carbon emissions by 50% using a new carbon capture technology. The breakthrough, developed over five years of research, could significantly impact global climate change efforts.\n\n" +
                       "Key findings:\n" +
                       "• New catalyst material increases carbon capture efficiency by 300%\n" +
                       "• Technology can be implemented in existing industrial facilities\n" +
                       "• Cost-effective solution compared to current methods\n" +
                       "• Potential to remove 2 billion tons of CO2 annually\n\n" +
                       "The research team is now working with governments and industries to implement this technology on a large scale.";
            
            case "Space Exploration Milestone":
                return "History was made today as the first manned mission to Mars successfully launched from Cape Canaveral. The international crew of six astronauts will embark on a 7-month journey to the Red Planet.\n\n" +
                       "Mission details:\n" +
                       "• First international collaboration of its kind\n" +
                       "• Advanced life support systems tested\n" +
                       "• Scientific experiments planned for Martian surface\n" +
                       "• Return mission scheduled for 2026\n\n" +
                       "This mission represents a significant step forward in human space exploration and interplanetary travel.";
            
            case "New Medical Discovery":
                return "A team of researchers has developed a revolutionary treatment for rare genetic diseases using advanced gene therapy techniques. The breakthrough could benefit millions of patients worldwide.\n\n" +
                       "Treatment highlights:\n" +
                       "• 95% success rate in clinical trials\n" +
                       "• Minimal side effects reported\n" +
                       "• One-time treatment with lasting effects\n" +
                       "• FDA approval expected within the year\n\n" +
                       "The research team plans to expand the treatment to other genetic conditions in the coming years.";
            
            case "Economic Growth Report":
                return "The latest global economic report shows promising signs of recovery, with a 3.5% growth rate in the first quarter. This marks the strongest economic performance in a decade.\n\n" +
                       "Key economic indicators:\n" +
                       "• Unemployment rate drops to 4.2%\n" +
                       "• Consumer confidence at 10-year high\n" +
                       "• Manufacturing sector shows 5% growth\n" +
                       "• International trade volume increases by 8%\n\n" +
                       "Economists predict continued growth through the year, though caution about potential challenges ahead.";
            
            case "Sports Championship":
                return "In a stunning upset, the underdog team claimed victory in the national championship final, winning 3-2 in overtime. The match, watched by millions worldwide, will go down in sports history.\n\n" +
                       "Game highlights:\n" +
                       "• Record-breaking attendance at the stadium\n" +
                       "• Dramatic last-minute equalizer\n" +
                       "• MVP performance by rookie player\n" +
                       "• Historic victory after 30-year drought\n\n" +
                       "The team's victory parade is scheduled for next week, with celebrations expected to draw thousands of fans.";
            
            case "Cultural Festival":
                return "The annual international arts festival concluded with record attendance, showcasing cultural diversity and artistic excellence from around the world.\n\n" +
                       "Festival highlights:\n" +
                       "• Over 100,000 visitors in 10 days\n" +
                       "• 500+ performances across 50 venues\n" +
                       "• Record-breaking art sales\n" +
                       "• New cultural exchange programs launched\n\n" +
                       "The festival's success has prompted organizers to expand next year's event to include more international participants.";
            
            case "Education Reform":
                return "New teaching methods implemented in schools nationwide have shown significant improvement in student performance, according to the latest education report.\n\n" +
                       "Key improvements:\n" +
                       "• 25% increase in test scores\n" +
                       "• Higher student engagement rates\n" +
                       "• Improved teacher satisfaction\n" +
                       "• Better learning outcomes across all subjects\n\n" +
                       "The education ministry plans to expand these methods to all schools by next academic year.";
            
            case "Environmental Protection":
                return "A community-led initiative has successfully restored the local wildlife habitat, creating a model for environmental conservation efforts worldwide.\n\n" +
                       "Project achievements:\n" +
                       "• 100 acres of land restored\n" +
                       "• 50+ species returned to the area\n" +
                       "• Community involvement in conservation\n" +
                       "• Sustainable tourism opportunities created\n\n" +
                       "The project has received international recognition and will be replicated in other regions.";
            
            default:
                return "Detailed content for this news article is currently being prepared. Please check back later for updates.";
        }
    }

    private void loadRelatedNews() {
        // TODO: Replace with actual related news loading logic
        List<News> relatedNews = new ArrayList<>();
        // Add dummy related news
        relatedNews.add(new News("Related News 1", "Content 1", "https://picsum.photos/300/200"));
        relatedNews.add(new News("Related News 2", "Content 2", "https://picsum.photos/300/200"));
        relatedNews.add(new News("Related News 3", "Content 3", "https://picsum.photos/300/200"));
        
        relatedNewsAdapter.setNewsList(relatedNews);
    }
} 