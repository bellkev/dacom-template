;   Copyright (c) 2014 Kevin Bell. All rights reserved.
;   See the file LICENSE for copying permission.



(ns leiningen.new.dacom
  (:require [leiningen.new.templates :refer [renderer name-to-path year ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "dacom"))

(defn dacom
  "Generate a new DACOM project"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)
              :year (year)}]
    (main/info "Generating a DACOM-based webapp called" name)
    ;; find -E . ".*" -type f | sed -E 's/.\/(.*)/["\1" (render "\1" data)]/' | sed 's/dacom/{{sanitized}}/'
    (->files data
             [".editorconfig" (render ".editorconfig" data)]
             [".gitignore" (render ".gitignore" data)]
             ["bower.json" (render "bower.json" data)]
             ["config/dev-config.edn" (render "config/dev-config.edn" data)]
             ["config/prod-config-template.edn" (render "config/prod-config-template.edn" data)]
             ["db-resources/schema.edn" (render "db-resources/schema.edn" data)]
             ["LICENSE" (render "LICENSE" data)]
             ["project.clj" (render "project.clj" data)]
             ["README.md" (render "README.md" data)]
             ["src/{{sanitized}}/client.cljs" (render "src/dacom/client.cljs" data)]
             ["src/{{sanitized}}/config.clj" (render "src/dacom/config.clj" data)]
             ["src/{{sanitized}}/server.clj" (render "src/dacom/server.clj" data)]
             ["utils/src/{{sanitized}}/db.clj" (render "utils/src/dacom/db.clj" data)]
             ["utils/src/{{sanitized}}/repl.clj" (render "utils/src/dacom/repl.clj" data)]
             ["utils/src/{{sanitized}}/repl.cljs" (render "utils/src/dacom/repl.cljs" data)]
             ["web-resources/pages/index.html" (render "web-resources/pages/index.html" data)]
             ["web-resources/stylesheets/style.less" (render "web-resources/stylesheets/style.less" data)])))
