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
   [:p "Coding Test"]])

(defn gaming-content []
  [:div
   [:p "Gaming Test"]])

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Vars
(defonce app-state
  (r/atom
     {:active-column "Mixing"}))

(def content-map
  {:Mixing music-content
   :Coding coding-content
   :Gaming gaming-content})

(def title-list ["Mixing", "Coding", "Gaming"])

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Home Page

(defn header-component []
  [:div
   [:h1 "Thomas La Piana"]])

(defn column-header [column-title]
  (let [val column-title]
  [:div.column-left
   [:h3.center {:style {:cursor "pointer"}
                :class (if (= (:active-column @app-state) val) "active-header")
                :on-click #(swap! app-state assoc-in [:active-column] val)}val]]))

(defn body-component []
  [:div
   [:h2
    {:style {:color "black",:text-align "center"}} "Things I do:"]
    [:div
     (for [title title-list]
      ^{:key title} [column-header title])]
   [:div.content-box [(get content-map (keyword (:active-column @app-state)))]]])

(defn app[]
  [:div
   [header-component]
   [body-component]])

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
