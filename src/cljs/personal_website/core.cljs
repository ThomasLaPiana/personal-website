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
   [:p "Coding Content"]])

(defn gaming-content []
  [:div
   [:p "Gaming Content"]])

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Vars
(defonce app-state
  (r/atom
     {:active-column "Mixing"}))

(def content-map
  {:Mixing music-content
   :Coding coding-content
   :Gaming gaming-content})

(def header-list ["Mixing", "Coding", "Gaming"])

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Home Page

(defn title-component []
  [:div
   [:h1 "Thomas La Piana"]])

(defn column-header [column-title]
  (let [val column-title]
  [:div.column-header
   [:h3.center {:style {:cursor "pointer"}
                :class (if (= (:active-column @app-state) val) "active-header")
                :on-click #(swap! app-state assoc-in [:active-column] val)}val]]))

(defn header-component []
  [:div
   [:h2 {:style {:color "black",:text-align "center"}} "Things I do:"]
   [:div
     (for [title header-list]
      ^{:key title} [column-header title])]])

(defn content-component []
  [:div.content-box
   [(get content-map (keyword (:active-column @app-state)))]])

(defn app[]
  [:div
   [title-component]
   [header-component]
   [content-component]])

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Initialize App

(defn dev-setup []
  (when ^boolean js/goog.DEBUG
    (enable-console-print!)
    (println "dev mode")
    ))

(defn reload []
  (r/render [app]
                  (.getElementById js/document "app")))

(defn ^:export main []
  (dev-setup)
  (reload))
