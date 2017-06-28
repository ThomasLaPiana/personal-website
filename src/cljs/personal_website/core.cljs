(ns personal-website.core
  (:require
   [reagent.core :as r]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Content

(defn music-content []
  [:div
   [:iframe {:width "100%"
             :height "450"
             :scrolling "no"
             :frameborder "no"
             :src "https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/users/254096186&amp;color=ff5500&amp;auto_play=false&amp;hide_related=false&amp;show_comments=true&amp;show_user=true&amp;show_reposts=false"}]])

(defn coding-content []
  [:div
   [:p "Test"]])

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Vars
(defonce body-content (r/atom [:div [:h3 "Click on a Title"]]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Home Page

(defn content-box [val]
  [:div.content-box
   [:p "This is a content box"]])

(defn header-component []
  [:div
   [:h1 "Thomas La Piana"]])

(defn music-column []
  (let [val "Mixing"]
  [:div.column-left
   [:h3.center {:style {:cursor "pointer"}
                :on-click #(reset! body-content music-content)}val]]))

(defn coding-column []
  (let [val "Coding"]
  [:div.column-center
   [:h3.center {:style {:cursor "pointer"}
                :on-click #(reset! body-content coding-content)}val]]))

(defn gaming-column []
  [:div.column-right
   [:h3.center "I play a lot of Games"]])

(defn body-component []
  [:div
   [:h2
    {:style {:color "black",:text-align "center"}} "Things I do:"]
    [:div
     [music-column]
     [coding-column]
     [gaming-column]]
   [:div [body-content]]])

(defn footer-component []
  [:div
   [:p.center "We chillin for now"]])

(defn home-page []
  [:div
   [header-component]
   [body-component]
   [footer-component]])

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Initialize App

(defn dev-setup []
  (when ^boolean js/goog.DEBUG
    (enable-console-print!)
    (println "dev mode")
    ))

(defn reload []
  (r/render [home-page]
                  (.getElementById js/document "app")))

(defn ^:export main []
  (dev-setup)
  (reload))
